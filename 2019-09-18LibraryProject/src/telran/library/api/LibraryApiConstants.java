package telran.library.api;

public interface LibraryApiConstants {
String ADD_AUTHOR = "/authors";
String GET_AUTHOR = "/authors/{name}";
String ADD_BOOK = "/books";
String GET_BOOK = "/books/{isbn}";
String ADD_PUBLISHER = "/publishers";
String GET_PUBLISHER = "/publishers";
String PICK_BOOK = "/books/pick";
String RETURN_BOOK = "/books/return";
String ACTIVE_READER = "/readers/active";
}
