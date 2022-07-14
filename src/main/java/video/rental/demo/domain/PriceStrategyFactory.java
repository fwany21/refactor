package video.rental.demo.domain;

public class PriceStrategyFactory {
    PriceStrategy createPriceStrategy(int priceCode) {
        PriceStrategy strategy;
        switch (priceCode) {
            case Video.REGULAR:
                strategy = new RegularPrice();
                break;
            case Video.NEW_RELEASE:
                strategy = new NewReleasePrice();
                break;
            case Video.CHILDREN:
                strategy = new ChildrenPrice();
                break;
            default:
                throw new IllegalArgumentException("No such code" + priceCode);
        }
        return strategy;
    }
}
