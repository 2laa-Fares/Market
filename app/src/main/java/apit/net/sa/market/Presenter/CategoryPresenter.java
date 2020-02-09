package apit.net.sa.market.Presenter;

import java.util.List;
import apit.net.sa.market.Interfaces.CategoryInterface;
import apit.net.sa.market.Models.Category;

/**
 * Implements presenter of get categories.
 */
public class CategoryPresenter implements CategoryInterface.categoryPresenter , CategoryInterface.categoryIntractor.categoryListener {

    private CategoryInterface.categoryView view;
    private CategoryInterface.categoryIntractor intractor;

    /**
     * Instantiates a new Category presenter.
     *
     * @param view       the category view
     * @param intractor the category intractor
     */
    public CategoryPresenter(CategoryInterface.categoryView view, CategoryInterface.categoryIntractor intractor) {
        this.view = view;
        this.intractor = intractor;
    }


    /**
     * Request categories.
     */
    @Override
    public void requestCategories() {
        intractor.getCategories(this);
    }

    /**
     * get lists success
     * @param categories lists of categories data
     */
    @Override
    public void onfinished(List<Category> categories) {
        if(view != null) view.getCategoriesSuccessfully(categories);
    }

    /**
     * failed.
     */
    @Override
    public void onFail(Throwable throwable) {
        if(view != null) view.failedToGetCategories(throwable);
    }
}
