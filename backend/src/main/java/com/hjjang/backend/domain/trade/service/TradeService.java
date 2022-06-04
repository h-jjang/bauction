package com.hjjang.backend.domain.trade.service;

import com.hjjang.backend.domain.trade.domain.entity.Trade;
import com.hjjang.backend.domain.trade.domain.entity.TradeState;
import com.hjjang.backend.domain.trade.domain.repositroy.TradeRepository;
import com.hjjang.backend.domain.trade.exception.TradeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.hjjang.backend.global.response.code.ErrorCode.TRADE_NOT_UPDATED;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository repository;

    public Trade save(Trade entity) {
        return Stream.of(entity)
                .map(repository::save)
                .findAny()
                .orElseThrow(() -> new TradeNotFoundException(null));
    }

    public Trade findById(long id) {
        return Stream.of(id)
                .map(repository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny()
                .orElseThrow(() -> new TradeNotFoundException(null));
    }

    public List<Trade> findAll() {
        return repository.findAll();
    }

    public Trade update(long id, Trade entity) {
        return Stream.of(id)
                .map(this::findById)
                .map(trade -> trade.update(entity))
                .map(this::save)
                .findAny()
                .orElseThrow(() -> new TradeNotFoundException(TRADE_NOT_UPDATED));
    }

    public void remove(long id) {
        Stream.of(id)
                .map(this::findById)
                .map(Trade::remove)
                .forEach(this::save);
    }

    public Trade changeState(long id, TradeState tradeState) {
        return Stream.of(id)
                .map(this::findById)
                .map(trade -> trade.setTradeState(tradeState))
                .map(this::save)
                .findAny()
                .orElseThrow(() -> new TradeNotFoundException(TRADE_NOT_UPDATED));
    }
}
