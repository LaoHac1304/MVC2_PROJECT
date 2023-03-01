/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanvd.utils;

/**
 *
 * @author simnh
 */
public class MyApplicationConstants {
    public class DispatchFeatures {
        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
    }
    
    public class LoginFeatures {
        public static final String INVALID_PAGE = "invalidPage";
        public static final String SEARCH_PAGE = "searchPage";
        public static final String SEARCH_STATIC_PAGE = "searchStaticPage";
    }
    
    public class SearchFeatures{
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchLastnameController";
    }
    
    public class DeleteFeatures{
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteAccountController";
    }
    public class UpdateFeatures{
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateAccountController";
        public static final String ERROR_PAGE = "errorPage";
    }
    public class AddBookFeatures{
        public static final String ADD_BOOK_TO_CART_CONTROLLER = "addBookController";
        public static final String SHOPPING_PAGE = "shoppingPage";
    }
    public class StartUpFeatures{
        public static final String STARTUP_SERVLET = "startupController";
    }
    public class LogoutFeatures{
        public static final String LOGOUT_CONTROLLER = "logoutController";
    }
    public class ViewCartFeatures{
        public static final String VIEW_CART_PAGE = "viewCartPage";
    }
    public class RemoveBookFeatures{
        public static final String REMOVE_BOOK_FROM_CART_COTROLLER = "removeCartController";
    }
    public class CreateAccountFeatures{
        public static final String CREATE_ACCOUNT_CONTROLLER = "createAccountController";
        public static final String CREATE_ACCOUNT_RESULT_PAGE = "createAccountResultPage";
    }
    public class CheckoutFeatures{
        public static final String CHECK_OUT_FROM_CART_CONTROLLER = "checkoutController";
    }
    
}
