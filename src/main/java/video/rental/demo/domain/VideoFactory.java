package video.rental.demo.domain;

public class VideoFactory {
    PriceStrategyFactory strategyFactory = new PriceStrategyFactory();

    public Video createVideo(String title, int videoType, int priceCode, Rating videoRating, LocalDate registeredDate) {
        Video video = new Video(title, videoType, priceCode, videoRating, registeredDate);

        video.setPriceStrategy(strategyFactory.createPriceStrategy(priceCode));
        return video;
    }
}
