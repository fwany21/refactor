package video.rental.demo;

import java.util.List;

public interface Repository {

	List<Customer> findAllCustomers();

	List<Video> findAllVideos();

	Customer findCustomerById(CmdUI cmdUI, int code);

	Video findVideoByTitle(CmdUI cmdUI, String title);

	void saveCustomer(CmdUI cmdUI, Customer customer);

	void saveVideo(CmdUI cmdUI, Video video);

}