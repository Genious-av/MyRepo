package telran.library.random;


import static telran.library.api.LibraryApiConstants.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import telran.library.dto.Book;
import telran.library.dto.LibReturnCode;
import telran.library.dto.PickReturnData;
import telran.library.dto.PublisherAuthor;
import telran.library.dto.Reader;
import telran.library.dto.Record;
import telran.library.dto.SubjectBook;

public class RandomGen {
static RestTemplate restTemplate=new RestTemplate();
	
	static String url="http://localhost:9090/";
	
	public static RandomLib randomLib =new RandomLib();

	
	public static String[] names= {"Alexey","Leo","Alexander","Nikolay","John","David", "Michael", "Valdimir", "Voldemar"};
	public static String[] surnames= {"Sidorov","Petrov", "Pushkin", "Targarien","Lanister", "Trump", "Backinghem"};
	public static String[] bookTitle= {"Book of roses", "Book of gardens", "New Book", "Ruslan and Ludmila", "Very interesting Book"};
	public static String[] countries= {"Israel", "Russia", "USA", "France", "Zimbabve"};
	public static String[] subjects= {"LITERATURE", "EDUCATION", "SCIENCE" };
	public static String[] languages= {"Hebrew","Russian", "English", "Polish", "Klingon"};
	public static String[] streets= {"52 Avenue", "Histadrut", "Saphir", "Jerusalem","Levi Eshkol","Lenina"};
		
		
	ArrayList<Reader> readersList=new ArrayList<>();
	ArrayList<PublisherAuthor> authorsList=new ArrayList<>();
	ArrayList<PublisherAuthor> publishersList=new ArrayList<>();
	ArrayList<Record> recordList=new ArrayList<>();
	ArrayList<Book> booksList=new ArrayList<>();
	
	public HttpHeaders getHeaders() {
		HttpHeaders headers= new HttpHeaders();
		String plainCred="123456:123456";
		String base64Cred=Base64.getEncoder().encodeToString(plainCred.getBytes());
		headers.add("Authorization", "Basic "+base64Cred);
		return headers;
		 
	}
	
	public void addReader(int num) {
		HttpHeaders headers=getHeaders();
		
		 for(int i=0;i<num;i++) {
		 Reader reader=new Reader(randomLib.nextRandomLongVar2(10), randomLib.nextStringFromSet(names)+" "+randomLib.nextStringFromSet(surnames), randomLib.nextRandomLongVar2(8).toString(),
					randomLib.nextRandomStringNew(5)+"@gmail.com",
					randomLib.nextStringFromSet(streets), 
					LocalDate.of(randomLib.nextIntRange(1910, 2019),randomLib.nextIntRange(1, 12), randomLib.nextIntRange(1, 28)));
		 
		 readersList.add(reader);
		 
		 HttpEntity<Reader> requestEntityReader=new HttpEntity<>(reader,headers);
		System.out.println(reader.toString());
		 ResponseEntity<LibReturnCode> response = restTemplate.exchange(url+ADD_READER, HttpMethod.POST, requestEntityReader, LibReturnCode.class);
		 //System.out.println(response.getBody());
		 }
	}
	 
	 
	 public void addPublisher(int num) {
		 
		
		 HttpHeaders headers=getHeaders();
		 for(int i=0;i<num;i++) {
			 PublisherAuthor publisher=new PublisherAuthor(randomLib.nextStringFromSet(names)+" "+randomLib.nextStringFromSet(surnames), randomLib.nextStringFromSet(countries));
			 HttpEntity<PublisherAuthor> requestEntityPublisher=new HttpEntity<>(publisher,headers);
			 ResponseEntity<LibReturnCode> response = restTemplate.exchange(url+ADD_PUBLISHER, HttpMethod.POST, requestEntityPublisher, LibReturnCode.class);
			 publishersList.add(publisher);
			 
			 
			System.out.println(publisher.toString());
			 //System.out.println(response.getBody());
		 }
	 }
	 
	 public void addAuthor(int num) {
		 HttpHeaders headers=getHeaders();
		 for(int i=0;i<num;i++) {
			 PublisherAuthor author=new PublisherAuthor(randomLib.nextStringFromSet(names)+" "+randomLib.nextStringFromSet(surnames), randomLib.nextStringFromSet(countries));
			 HttpEntity<PublisherAuthor> requestEntityAuthor=new HttpEntity<>(author,headers);
			 ResponseEntity<LibReturnCode> response = restTemplate.exchange(url+ADD_AUTHOR, HttpMethod.POST, requestEntityAuthor, LibReturnCode.class);
			 authorsList.add(author);
			 System.out.println(author.toString());
			 
			 
			 //System.out.println(response.getBody());
		 }
	 }
	 
	 
	 public void addBook(int num) {
		for(int k=0;k<num;k++) {
		HttpHeaders headers=getHeaders();
				
//		ResponseEntity<List<PublisherAuthor>> responsePub = restTemplate.exchange(url+ALL_PUBLISHERS, HttpMethod.GET, new HttpEntity<>(headers),
//				    new ParameterizedTypeReference<List<PublisherAuthor>>() {});
//		List<PublisherAuthor> publishers = responsePub.getBody();
		List<String> publishersName=publishersList.stream().map(publisher->publisher.getName()).collect(Collectors.toList());
		String[] publishersNameArray=new String[publishersName.size()];
		
//		ResponseEntity<List<PublisherAuthor>> responseAuth = restTemplate.exchange(url+ALL_AUTHORS, HttpMethod.GET, new HttpEntity<>(headers),
//			    new ParameterizedTypeReference<List<PublisherAuthor>>() {});
//		List<PublisherAuthor> authors = responseAuth.getBody();
		List<String> authorsName=authorsList.stream().map(auth->auth.getName()).collect(Collectors.toList());
		String[] authorsNameArray=new String[authorsName.size()];
		
		int numOfAuthors=randomLib.nextIntRange(1, 3);
		Set<String> authorsSet = new HashSet<>();
		
		
		
		
		for(int i=0;i<numOfAuthors;i++) {
			authorsSet.add(randomLib.nextStringFromSet(authorsName.toArray(authorsNameArray)));
		}
		
		
		 
		 Book book = new Book(randomLib.nextRandomLongVar2(10), randomLib.nextIntRange(1950, 2019), randomLib.nextStringFromSet(publishersName.toArray(publishersNameArray)), authorsSet, randomLib.nextStringFromSet(bookTitle),
					randomLib.nextIntRange(1, 100), SubjectBook.valueOf(randomLib.nextStringFromSet(subjects)), 
					randomLib.nextStringFromSet(languages), randomLib.nextIntRange(5, 30));
		 
		 booksList.add(book);
		 System.out.println(book.toString());
		 
		 
		 HttpEntity<Book> requestEntityBook=new HttpEntity<>(book,headers);
		 ResponseEntity<LibReturnCode> response = restTemplate.exchange(url+ADD_BOOK, HttpMethod.POST, requestEntityBook, LibReturnCode.class);
		//System.out.println(response.getBody());
		 
		 
		}	 
	 } 
	 
	 
	 public void addRecord(int num,LocalDate ld) {
		 for(int k=0;k<num;k++) {
		 
			 HttpHeaders headers=getHeaders();
		 //getting ISBN
//	ResponseEntity<List<Book>> responseBook = restTemplate.exchange(url+All_BOOKS, HttpMethod.GET, new HttpEntity<>(headers),
//				    new ParameterizedTypeReference<List<Book>>() {});
//		List<Book> books = responseBook.getBody();
		List<Long> ISBNs=booksList.stream().map(book->book.getIsbn()).collect(Collectors.toList());
		String[] ISBNArray=new String[ISBNs.size()];
		 
//		ResponseEntity<List<Reader>> responseReader = restTemplate.exchange(url+ALL_READERS, HttpMethod.GET, new HttpEntity<>(headers),
//			    new ParameterizedTypeReference<List<Reader>>() {});
//	List<Reader> readers = responseReader.getBody();
	List<Long> readersID=readersList.stream().map(reader->reader.getId()).collect(Collectors.toList());
		 
	
		 
		 Record record = new Record(ISBNs.get(randomLib.nextIntRange(0, ISBNs.size()-1)), readersID.get(randomLib.nextIntRange(0, readersID.size()-1)), ld);
		 
		 recordList.add(record);
		 
		 System.out.println(record);
		 
		 HttpEntity<Record> requestEntityRecord=new HttpEntity<>(record,headers);
		 
		 ResponseEntity<LibReturnCode> response = restTemplate.exchange(url+PICK_BOOK, HttpMethod.POST, requestEntityRecord, LibReturnCode.class);
		//System.out.println(response.getBody());
		 
	 }
	 }
	 
	 
	 
	 public void returnBook(int num, LocalDate ld) {
		 for(int k=0;k<num;k++) {
		 HttpHeaders headers=getHeaders();
		 headers.add("Content-Type", "application/json");

//		 ResponseEntity<List<Record>> responseRecord = restTemplate.exchange(url+ALL_RECORDS, HttpMethod.GET, new HttpEntity<>(headers),
//				    new ParameterizedTypeReference<List<Record>>() {});
		 
		List<Record> openRecords = recordList.stream().filter(record->record.getDateOfReturning()==null).collect(Collectors.toList());
		 
		//System.out.println(openRecords.size());
		
		
		List<Long> isbns=openRecords.stream().map(record->record.getIsbn()).collect(Collectors.toList());
		
		if (isbns.size()==0) break;
		
		Long selectedISBN=isbns.get(randomLib.nextIntRange(0, isbns.size()-1));
		
		Long readerID=openRecords.stream().filter(record->record.getIsbn()==selectedISBN).map(record->record.getReaderId()).findFirst().orElse(null);
		
//		LocalDate ldOfRecord=openRecords.stream().filter(record->record.getIsbn()==selectedISBN).map(record->record.getDatePickingUp()).findFirst().orElse(null);
//			System.out.println("DATE:"+ldOfRecord);
//		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		PickReturnData returnData=new PickReturnData(readerID, selectedISBN, ld);
		HttpEntity<PickReturnData> requestEntityReturn=new HttpEntity<>(returnData,headers);
		 ResponseEntity<LibReturnCode> response = restTemplate.exchange(url+RETURN_BOOK, HttpMethod.POST, requestEntityReturn, LibReturnCode.class);
		System.out.println(returnData.toString());
		 
		 } 
 } 
		 

	 
	 
	 
	 public void dynamicGen() {
		 LocalDate ld=LocalDate.of(2019, 01, 01);
		 for(int day=0;day<1;day++) {
			ld.plusDays(ld.getDayOfYear()+1);
			 
			addPublisher(randomLib.nextIntRange(1, 10));
			addAuthor(randomLib.nextIntRange(1, 10));
			addBook(randomLib.nextIntRange(1, 10));
			addReader(randomLib.nextIntRange(1, 10));
			addRecord(randomLib.nextIntRange(1, 30), ld);
			//returnBook(randomLib.nextIntRange(1, 5),ld.plusDays(1));
			 
			 
			 
		 }
		 
	 }
	 
					

		
}
