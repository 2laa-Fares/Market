package apit.net.sa.market.Interfaces;

import java.util.List;

import apit.net.sa.market.Models.Category;

/**
 * Category interface.
 */
public interface CategoryInterface {
    /**
     * The interface category presenter.
     */
    interface categoryPresenter{
        /**
         * Request categories.
         */
        void requestCategories();
    }

    /**
     * The interface category Intractor is built for fetching data from web services.
     */
    interface categoryIntractor{
        /**
         * The interface category listner.
         */
        interface categoryListener{
            /**
             * On finished.
             *
             * @param categories the list of categories.
             */
            void onfinished(List<Category> categories);

            /**
             * On fail.
             *
             * @param throwable the error occurred.
             */
            void onFail(Throwable throwable);
        }

        /**
         * Gets categories.
         *
         * @param categoryListener the category listener
         */
        void getCategories(categoryListener categoryListener);
    }

    /**
     * The interface category view.
     */
    interface categoryView{
        /**
         * Gets categories successfully.
         *
         * @param categories categories gets from server.
         */
        void getCategoriesSuccessfully(List<Category> categories);

        /**
         * Failed to get categories.
         *
         * @param throwable the error occurred.
         */
        void failedToGetCategories(Throwable throwable);
    }
}
