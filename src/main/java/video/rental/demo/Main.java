package video.rental.demo;

import video.rental.demo.application.Interactor;
import video.rental.demo.domain.Repository;
import video.rental.demo.infrastructure.RepositoryDBImpl;
import video.rental.demo.presentation.CmdUI;
import video.rental.demo.presentation.GraphicUI;
import video.rental.demo.util.SampleGenerator;

public class Main {

	public static void main(String[] args) {
		Repository repository = new RepositoryDBImpl();
		Interactor interactor = new Interactor(repository);
		new SampleGenerator(repository).generateSamples();
		// CmdUI ui = new CmdUI(interactor);
		GraphicUI ui = new GraphicUI(interactor);
		ui.start();
	}
}
