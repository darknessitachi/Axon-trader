package org.axonframework.samples.trader.app.query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

/**
 * @author Jettro Coenradie
 */
@Repository
@Transactional(readOnly = true)
public class OrderBookRepositoryJpa implements OrderBookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings({"unchecked"})
    @Override
    public List<OrderBookEntry> listAllOrderBooks() {
        return entityManager.createQuery("SELECT e FROM OrderBookEntry e")
                .setMaxResults(250)
                .getResultList();
    }

    @Override
    public OrderBookEntry findByIdentifier(UUID aggregateIdentifier) {
        OrderBookEntry orderBook = (OrderBookEntry) entityManager.createQuery(
                "SELECT e FROM OrderBookEntry e where e.identifier = :aggregateIdentifier")
                .setParameter("aggregateIdentifier", aggregateIdentifier)
                .getSingleResult();
        orderBook.getOrders().size(); // to support lazy loading if we collect all order books
        return orderBook;
    }
}