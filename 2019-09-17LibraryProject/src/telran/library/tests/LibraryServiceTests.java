package telran.library.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

import telran.library.dto.Book;
import telran.library.dto.LibReturnCode;
import telran.library.dto.PublisherAuthor;
import telran.library.dto.Reader;
import telran.library.dto.SubjectBook;
import telran.library.service.interfaces.ILibrary;

import java.time.LocalDate;
import java.util.*;
//Three annotations below are because of LibraryServiceTests in not root package
@SpringBootApplication(scanBasePackages = "telran.library")  // components root directory
//@EnableJpaRepositories(basePackages = "telran.library.service")  // repositories root directory
@EntityScan(basePackages = "telran.library.domain.entities")// entities root directory

class LibraryServiceTests {
	
	ConfigurableApplicationContext configurableApplicationContext;
	
	private static final long ISBN1 = 1;
	private static final long ISBN2 = 2;
	private static final long ISBN3 = 2;
	private static final int PUBLISH_YEAR1 = 2010;
	private static final int PUBLISH_YEAR2 = 2011;
	private static final int PUBLISH_YEAR3 = 2012;
	private static final String PUBLISH_NAME1 = "publisher1";
	private static final String PUBLISH_NAME2 = "publisher2";
	private static final String PUBLISH_NAME3 = "publisher3";
	private static final String AUTHOR_NAME1 = "name1";
	private static final String AUTHOR_NAME2 = "name2";
	private static final String AUTHOR_NAME3 = "name3";
	private static final String TITLE1 = "title1";
	private static final String TITLE2 = "title2";
	private static final String TITLE3 = "title3";
	private static final int AMOUNT1 = 10;
	private static final int AMOUNT2 = 20;
	private static final int AMOUNT3 = 30;
	private static final SubjectBook SUBJECT1 = SubjectBook.LITERATURE;
	private static final SubjectBook SUBJECT2 = SubjectBook.SCIENCE;
	private static final SubjectBook SUBJECT3 = SubjectBook.EDUCATION;
	private static final String LANGUGE1 = "en";
	private static final String LANGUGE2= "ru";
	private static final String LANGUGE3 = "il";
	private static final int MAX_DAYS1 = 20;
	private static final int MAX_DAYS2 = 30;
	private static final int MAX_DAYS3 = 25;
	private static final String COUNTRY1 = "country1";
	private static final String COUNTRY2 = "country2";
	private static final String COUNTRY3 = "country3";
	private static final long READER_ID1 = 1;
	private static final long READER_ID2 = 2;
	private static final long READER_ID3 = 3;
	private static final String READER_NAME1 = "name1";
	private static final String READER_NAME2 = "name2";
	private static final String READER_NAME3 = "name3";
	private static final String PHONE1 = "1234567";
	private static final String PHONE2 = "7654321";
	private static final String PHONE3 = "1237654";
	private static final String EMAIL1 = "name1@gmail.com";
	private static final String EMAIL2 = "name2@gmail.com";
	private static final String EMAIL3 = "name3@gmail.com";
	private static final String ADDRESS1 = "address1";
	private static final String ADDRESS2 = "address2";
	private static final String ADDRESS3 = "address3";
	private static final int BIRTH_YEAR1 = 1980;
	private static final int BIRTH_YEAR2 = 1970;
	private static final int BIRTH_YEAR3= 1990;
	private static final LocalDate BIRTH_DATE1 = LocalDate.of(BIRTH_YEAR1, 1, 1);
	private static final LocalDate BIRTH_DATE2 = LocalDate.of(BIRTH_YEAR2, 1, 1);
	private static final LocalDate BIRTH_DATE3 = LocalDate.of(BIRTH_YEAR3, 1, 1);
	
	ILibrary library;
	
	
	Set<String> authors1 = new HashSet<>(Arrays.asList(AUTHOR_NAME1));
	Book book1 = new Book(ISBN1,
			PUBLISH_YEAR1, PUBLISH_NAME2,
			authors1, TITLE1, AMOUNT1, 
			SUBJECT1, LANGUGE1, MAX_DAYS1);
	
	Book book2=(new Book(ISBN2,
			PUBLISH_YEAR2, PUBLISH_NAME1,
			authors1, TITLE2, AMOUNT2, 
			SUBJECT2, LANGUGE2, MAX_DAYS2));
	PublisherAuthor publisher1 = new PublisherAuthor(PUBLISH_NAME1, COUNTRY1);
	PublisherAuthor publisher2 = new PublisherAuthor(PUBLISH_NAME2, COUNTRY2);
	PublisherAuthor author1 = new PublisherAuthor(AUTHOR_NAME1, COUNTRY1);
	Reader reader1 = new Reader(READER_ID1, READER_NAME1, PHONE1,
			EMAIL1, ADDRESS1, BIRTH_DATE1);
	Reader reader2 = new Reader(READER_ID2, READER_NAME2, PHONE2,
			EMAIL2, ADDRESS2, BIRTH_DATE2);
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		configurableApplicationContext = SpringApplication.run(LibraryServiceTests.class);
		library = configurableApplicationContext.getBean(ILibrary.class);
		library.addAuthor(author1);
		library.addPublisher(publisher1);
		library.addBookItem(book1);
		library.addReader(reader1);
		library.addBookItem(book1);
	
	}

	@AfterEach
	void tearDown() throws Exception {
		configurableApplicationContext.close();
	}

	@Test
	void testAddBookItem() {
		
		assertEquals(LibReturnCode.OK, library.addBookItem(book2));
		

	}

	@Test
	void testAddBookExemplar() {
		assertEquals(LibReturnCode.OK, library.addBookExemplar(ISBN1, AMOUNT1));
	}

	@Test
	void testGetBookItem() {
		assertEquals(book2, library.getBookItem(ISBN1));
	}

	@Test
	void testMoveToArchive() {
		assertEquals(LibReturnCode.OK, library.moveToArchive(ISBN1));
	}

	@Test
	void testRemoveExemplar() {
		assertEquals(LibReturnCode.OK, library.removeExemplar(ISBN1));
	}

//	@Test
//	void testLostExemplar() {
//		fail("Not yet implemented");
//	}
//
	@Test
	void testAddReader() {
		assertEquals(LibReturnCode.OK, library.addReader(reader2));
	}

	@Test
	void testGetReader() {
		assertEquals(reader1, library.getReader(READER_ID1));
	}

	@Test
	void testUpdateReaderEmail() {
	assertEquals(LibReturnCode.OK, library.updateReaderEmail(READER_ID1, EMAIL3));
	}

	@Test
	void testUpdateReaderPhone() {
		assertEquals(LibReturnCode.OK, library.updateReaderPhone(READER_ID3, PHONE2));
	}
//
//	@Test
//	void testUpdateReaderAddress() {
//		fail("Not yet implemented");
//	}
//
	@Test
	void testAddPublisher() {
	assertEquals(LibReturnCode.OK, library.addPublisher(publisher2));
	}

	@Test
	void testGetPublisherByName() {
		assertEquals(publisher1, library.getPublisherByName(PUBLISH_NAME1));
	}

	@Test
	void testGetPublishersByCountry() {
		assertEquals(publisher1, library.getPublishersByCountry(COUNTRY1));
	}

	@Test
	void testGetPublisherByBook() {
		assertEquals(publisher1,library.getPublisherByBook(ISBN1));
	}

	@Test
	void testAddAuthor() {
		assertEquals(LibReturnCode.OK, library.addAuthor(new PublisherAuthor(AUTHOR_NAME2, COUNTRY2)));
	}

	@Test
	void testGetAuthorsByName() {
		assertEquals(author1, library.getAuthorsByName(AUTHOR_NAME1));
	}

	@Test
	void testGetAuthorsByCountry() {
		assertEquals(author1, library.getAuthorsByCountry(COUNTRY1));
	}

	@Test
	void testGetAuthorsByBook() {
		assertEquals(author1, library.getAuthorsByBook(ISBN1));
	}

	@Test
	void testPickupBook() {
		assertEquals(LibReturnCode.OK,library.pickupBook(ISBN2, READER_ID1, LocalDate.of(2019, 10, 10)));
	}
//
//	@Test
//	void testReturnBook() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindRecordsByBook() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindRecordsByReader() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindRecordsByReturnDate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindOpenRecords() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetBooksNotPickedUp() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testIsExistBookInArchive() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetDelayedBooksByReader() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetReadersDelayingBooks() {
//		fail("Not yet implemented");
//	}

}
