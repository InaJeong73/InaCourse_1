package com.ajoufinder.domain.board.repository;

import com.ajoufinder.domain.board.entity.Board;
import com.ajoufinder.domain.board.repository.custom.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>, BoardRepositoryCustom {
}
