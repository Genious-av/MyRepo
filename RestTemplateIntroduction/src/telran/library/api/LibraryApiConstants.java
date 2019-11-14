package telran.library.api;

public interface LibraryApiConstants {
	String ADD_BOOK = "/books";
    String ADD_BOOK_EXEMPLAR = "/books/exemplar";
    String MOVE_TO_ARCHIVE = "/archive/to";
    String REMOVE_BOOK = "/book/remove";
    String LOST_BOOK = "/book/lost";
    String ADD_READER = "/reader";
    String GET_READER = "/reader";
    String EMAIL_UPDATE = "readeremail/update";
    String PHONE_UPDATE = "readerephone/update";
    String ADDRESS_UPDATE = "readeraddress/update";
    String GET_BOOK = "/books/{isbn}";
    String ADD_PUBLISHER ="/publishers";
    String GET_PUBLISHER ="/publishers";
    String GET_PUBLISHER_COUNTRY ="/publishers/country";
    String GET_PUBLISHER_BOOK ="/publishers/book";
    String ADD_AUTHOR = "/authors";
    String GET_AUTHORS = "/authors";
    String GET_AUTHORS_COUNTRY ="/authors/country";
    String GET_AUTHORS_BOOK ="/authors/book";
    String PICK_BOOK = "/books/pick";
    String RETURN_BOOK = "/books/return";
    String RECORDS_BOOK = "records/book";
    String RECORDS_READER = "records/reader";
    String ACTIVE_READER = "/readers/active";
    
    String ALL_READERS="/readers/all";
    String ALL_RECORDS="/records/all";
    String ALL_AUTHORS="/authors/all";
    String ALL_PUBLISHERS="/publishers/all";
    String All_BOOKS="/book/lost";

}
