package com.hjjang.backend.domain.trade.domain.repositroy;

import com.hjjang.backend.domain.trade.domain.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
