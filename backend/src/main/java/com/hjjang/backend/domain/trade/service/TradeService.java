package com.hjjang.backend.domain.trade.service;

import com.hjjang.backend.domain.trade.domain.entity.Trade;
import com.hjjang.backend.domain.trade.domain.repositroy.TradeRepository;
import com.hjjang.backend.domain.trade.exception.TradeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    public void update(long id, Trade entity) {
        Stream.of(id)
                .map(this::findById)
                .forEach(trade -> trade.update(entity));
    }

    public void remove(long id) {
        Stream.of(id)
                .map(this::findById)
                .forEach(Trade::remove);
    }
}
