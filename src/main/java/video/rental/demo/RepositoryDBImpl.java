package video.rental.demo;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

public class RepositoryDBImpl implements Repository {

	/*
	 * Database Access private methods
	 */

	// JPA EntityManager
	static final EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

	@Override
	public List<Customer> findAllCustomers() {
		TypedQuery<Customer> query = getEm().createQuery("SELECT c FROM Customer c", Customer.class);
		return query.getResultList();
	}

	@Override
	public List<Video> findAllVideos() {
		TypedQuery<Video> query = getEm().createQuery("SELECT c FROM Video c", Video.class);
		return query.getResultList();
	}

	@Override
	public Customer findCustomerById(CmdUI cmdUI, int code) {
		return find(() -> getEm().find(Customer.class, code));
	}

	@Override
	public Video findVideoByTitle(CmdUI cmdUI, String title) {
		return find(() -> getEm().find(Video.class, title));
	}

	@Override
	public void saveCustomer(CmdUI cmdUI, Customer customer) {
		doIt(customer, getEm()::persist);
	}

	@Override
	public void saveVideo(CmdUI cmdUI, Video video) {
		doIt(video, getEm()::persist);
	}

	private <T> T find(Supplier<T> action) {
		T value = null;
		try {
			getEm().getTransaction().begin();
			value = action.get();
			getEm().getTransaction().commit();
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		}
		return value;
	}

	private <T> void doIt(T value, Consumer<T> action) {
		try {
			getEm().getTransaction().begin();
			action.accept(value);
			getEm().getTransaction().commit();
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		}
	}

	private static EntityManager getEm() {
		return em;
	}

}
