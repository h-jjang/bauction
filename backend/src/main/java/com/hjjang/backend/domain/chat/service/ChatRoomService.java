package com.hjjang.backend.domain.chat.service;

import com.hjjang.backend.domain.chat.domain.entity.ChatRoom;
import com.hjjang.backend.domain.chat.domain.entity.EntranceChatRoom;
import com.hjjang.backend.domain.chat.domain.entity.ChatRoomUser;
import com.hjjang.backend.domain.chat.domain.repository.EntranceChatRoomRepository;
import com.hjjang.backend.domain.chat.domain.repository.ChatRoomRepository;
import com.hjjang.backend.domain.chat.domain.repository.ChatRoomUserRepository;
import com.hjjang.backend.domain.chat.dto.CreateTradeChatRoomResponse;
import com.hjjang.backend.domain.chat.dto.HideTradeChatRoomResponse;
import com.hjjang.backend.domain.chat.dto.TradeChatRoomEntranceListResponse;
import com.hjjang.backend.domain.chat.exception.CannotCreateChatRoomBySelfException;
import com.hjjang.backend.domain.chat.exception.IsAlreadyHiddenChatRoomException;
import com.hjjang.backend.domain.chat.exception.NotFoundChatRoomEntityException;
import com.hjjang.backend.domain.chat.exception.NotFoundSellerEntityException;
import com.hjjang.backend.domain.user.dto.UserProfileInfo;
import com.hjjang.backend.domain.user.entity.User;
import com.hjjang.backend.domain.user.repository.UserRepository;
import com.hjjang.backend.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final UserUtil userUtil;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomUserRepository chatRoomUserRepository;
    private final UserRepository userRepository;
    private final EntranceChatRoomRepository entranceChatRoomRepository;

    /**
     * 중고거래를 하기 위한 1대1 채팅방 생성
     * 구매를 원하는 사용자가 사용하는 api로 buyer의 값은 jwt로 알아내고 매개변수로는 판매자의 id를 받는다.
     *
     * @param sellerId 판매자의 id
     * @return CreateTradeChatRoomResponse
     */
    @Transactional
    public CreateTradeChatRoomResponse createChatTradeRoomBySellerId(Long sellerId) {
        // buyer를 Token으로 조회해서 buyer로 채팅방 생성
        // exception 1) 자기 자신과는 채팅할 수 없다.
        User buyer = userUtil.getLoginUserByToken();
        ChatRoom createdRoom = createChatRoomByBuyer(buyer, sellerId);

        // seller를 조회해서 chatRoom과 user 간의 연관 관계 저장
        User seller = userRepository.findUserById(sellerId).orElseThrow(NotFoundSellerEntityException::new);
        ChatRoomUser savedChatRoomUser = createChatRoomUserWithSeller(createdRoom, seller);

        // 반환값을 위한 DTO 생성
        List<UserProfileInfo> userProfileInfoList = new ArrayList<>();
        userProfileInfoList.add(new UserProfileInfo(seller));

        return CreateTradeChatRoomResponse.builder()
                .chatRoomId(savedChatRoomUser.getChatRoom().getId())
                .createdByUser(new UserProfileInfo(buyer))
                .joinUsers(userProfileInfoList)
                .build();
    }

    private ChatRoomUser createChatRoomUserWithSeller(ChatRoom createdRoom, User seller) {
        ChatRoomUser chatRoomUser = ChatRoomUser.builder()
                .user(seller)
                .chatRoom(createdRoom)
                .build();
        return chatRoomUserRepository.save(chatRoomUser);
    }

    private ChatRoom createChatRoomByBuyer(User buyer, Long sellerId) {
        // 자기 자신과는 채팅할 수 없다.
        if (sellerId.equals(buyer.getId())) {
            throw new CannotCreateChatRoomBySelfException();
        }
        ChatRoom newChatRoom = ChatRoom.builder()
                .createdByUser(buyer)
                .build();
        return chatRoomRepository.save(newChatRoom);
    }

    /**
     * 채팅방 삭제 기능 / 숨기기
     *
     * @param chatRoomId 삭제할 채팅방 id
     * @return HideTradeChatRoomResponse
     */
    @Transactional
    public HideTradeChatRoomResponse hideChatRoom(Long chatRoomId) {
        ChatRoom foundChatRoom = chatRoomRepository.findChatRoomById(chatRoomId)
                .orElseThrow(NotFoundChatRoomEntityException::new);
        if (foundChatRoom.getIsHidden()) {
            throw new IsAlreadyHiddenChatRoomException();
        }
        foundChatRoom.hideChatRoom();
        return new HideTradeChatRoomResponse(chatRoomId, true);
    }

    // 로그인 되어있는 사용자 id로 채팅방 조회 기능
    public TradeChatRoomEntranceListResponse readChatRoomPaging(Pageable pageable) {
        User loginUser = userUtil.getLoginUserByToken();
        // todo N+1 이슈 해결해야함
        Page<EntranceChatRoom> chatRoomEntrances = entranceChatRoomRepository.findAllByUserId(loginUser.getId(), pageable);
        return new TradeChatRoomEntranceListResponse(chatRoomEntrances.toList());
    }

    // todo 채팅방 id로 신고 기능
    public void reportChatRoom() {

    }


}
