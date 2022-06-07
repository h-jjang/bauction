package com.hjjang.backend.domain.trade.controller;

import com.hjjang.backend.domain.trade.domain.entity.TradeState;
import com.hjjang.backend.domain.trade.dto.TradeMapper;
import com.hjjang.backend.domain.trade.dto.TradeRequestDto;
import com.hjjang.backend.domain.trade.exception.TradeNotFoundException;
import com.hjjang.backend.domain.trade.service.TradeService;
import com.hjjang.backend.global.response.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hjjang.backend.global.response.code.ErrorCode.*;
import static com.hjjang.backend.global.response.code.SuccessCode.*;
import static com.hjjang.backend.global.response.response.SuccessResponse.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/trades")
@RequiredArgsConstructor
public class TradeController {

    private final TradeService service;
    private final TradeMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public SuccessResponse save(@Validated @RequestBody TradeRequestDto requestDto) {
        return Stream.of(requestDto)
                .map(mapper::toEntity)
                .map(service::save)
                .map(mapper::fromEntity)
                .map(dto -> of(TRADE_CREATE_SUCCESS, dto))
                .findAny()
                .orElseThrow(() -> new TradeNotFoundException(TRADE_NOT_CREATED));
    }

    @GetMapping
    @ResponseStatus(OK)
    public SuccessResponse findALl() {
        return of(TRADE_FIND_SUCCESS,
                service.findAll()
                        .stream()
                        .map(mapper::fromEntity)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public SuccessResponse findById(@PathVariable Long id) {
        return Stream.of(id)
                .map(service::findById)
                .map(mapper::fromEntity)
                .map(dto -> of(TRADE_FIND_SUCCESS, dto))
                .findAny()
                .orElseThrow(() -> new TradeNotFoundException(TRADE_NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public SuccessResponse updateById(@PathVariable Long id, @Validated @RequestBody TradeRequestDto requestDto) {
        return Stream.of(requestDto)
                .map(mapper::toEntity)
                .map(entity -> service.update(id, entity))
                .map(mapper::fromEntity)
                .map(dto -> of(TRADE_UPDATE_SUCCESS, dto))
                .findAny()
                .orElseThrow(() -> new TradeNotFoundException(TRADE_NOT_UPDATED));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public SuccessResponse deleteById(@PathVariable Long id) {
        Stream.of(id)
                .forEach(service::remove);
        return of(TRADE_DELETE_SUCCESS);
    }

    @PatchMapping("/{id}/pending")
    @ResponseStatus(CREATED)
    public SuccessResponse changeStateByIdPending(@PathVariable Long id) {
        return Stream.of(id)
                .map(i -> service.changeState(i, TradeState.PENDING))
                .map(mapper::fromEntity)
                .map(dto -> of(TRADE_UPDATE_SUCCESS, dto))
                .findAny()
                .orElseThrow(() -> new TradeNotFoundException(TRADE_NOT_UPDATED));
    }

    @PatchMapping("/{id}/reserve")
    @ResponseStatus(CREATED)
    public SuccessResponse changeStateByIdReserve(@PathVariable Long id) {
        return Stream.of(id)
                .map(i -> service.changeState(i, TradeState.RESERVE))
                .map(mapper::fromEntity)
                .map(dto -> of(TRADE_UPDATE_SUCCESS, dto))
                .findAny()
                .orElseThrow(() -> new TradeNotFoundException(TRADE_NOT_UPDATED));

    }

    @PatchMapping("/{id}/approve")
    @ResponseStatus(CREATED)
    public SuccessResponse changeStateByIdApprove(@PathVariable Long id) {
        return Stream.of(id)
                .map(i -> service.changeState(i, TradeState.APPROVE))
                .map(mapper::fromEntity)
                .map(dto -> of(TRADE_UPDATE_SUCCESS, dto))
                .findAny()
                .orElseThrow(() -> new TradeNotFoundException(TRADE_NOT_UPDATED));
    }
    @PatchMapping("/{id}/cancel")
    @ResponseStatus(CREATED)
    public SuccessResponse changeStateByIdCancel(@PathVariable Long id) {
        return Stream.of(id)
                .map(i -> service.changeState(i, TradeState.CANCEL))
                .map(mapper::fromEntity)
                .map(dto -> of(TRADE_UPDATE_SUCCESS, dto))
                .findAny()
                .orElseThrow(() -> new TradeNotFoundException(TRADE_NOT_UPDATED));
    }
}
