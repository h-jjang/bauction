package com.hjjang.backend.domain.trade.controller;

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
                .map(responseDto -> of(TRADE_CREATE_SUCCESS, responseDto))
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
                .map(responseDto -> of(TRADE_FIND_SUCCESS, responseDto))
                .findAny()
                .orElseThrow(() -> new TradeNotFoundException(TRADE_NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public SuccessResponse updateById(@PathVariable Long id, @Validated @RequestBody TradeRequestDto requestDto) {
        Stream.of(requestDto)
                .map(mapper::toEntity)
                .forEach(entity -> service.update(id, entity));
        return of(TRADE_UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public SuccessResponse deleteById(@PathVariable Long id) {
        Stream.of(id)
                .forEach(service::remove);
        return of(TRADE_DELETE_SUCCESS);
    }

}
