package video.rental.demo.infrastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import video.rental.demo.domain.Customer;
import video.rental.demo.domain.Repository;
import video.rental.demo.domain.Video;

public class RepositoryMemImpl implements Repository {
	private Map<Integer, Customer> customers = new HashMap<>();
	private Map<String, Video> videos = new HashMap<>();
	
	@Override
	public List<Customer> findAllCustomers() {
		return new ArrayList<Customer>(customers.values());
	}

	@Override
	public List<Video> findAllVideos() {
		return new ArrayList<Video>(videos.values());
	}

	@Override
	public Customer findCustomerById(int code) {
		return customers.get(code);
	}

	@Override
	public Video findVideoByTitle(String title) {
		return videos.get(title);
	}

	@Override
	public void saveCustomer(Customer customer) {
		customers.put(customer.getCode(), new Customer(customer));
	}

	@Override
	public void saveVideo(Video video) {
		videos.put(video.getTitle(), new Video(video));
	}

}
