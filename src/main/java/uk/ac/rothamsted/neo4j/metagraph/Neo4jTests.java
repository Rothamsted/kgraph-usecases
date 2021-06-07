package uk.ac.rothamsted.neo4j.metagraph;

import static org.neo4j.driver.Values.parameters;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

public class Neo4jTests implements AutoCloseable{

	private final Driver driver;

	/**
	 * TODO: Nice idea to have this as an autocloseable that keeps the driver open during its life.
	 * However, methods like {@link #classesSummary()} need to go to some other class (eg, Neo4jMetaGraphUtils).
	 * this one at the moment looks like a playground and test class (that's why I've renamed it to Neo4jTests).
	 * 
	 * Moreover, I've also renamed it according to Java conventions (https://www.geeksforgeeks.org/java-naming-conventions/).
	 *  
	 */
	public Neo4jTests(String uri,String user,String password){
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user,password));
    }
	
    public void addProduct(String product){
        try (Session session = driver.session()){
            session.run("CREATE (Product {product: $product})", parameters("product", product));
        }
    }
    
    public void addCustomer(String customer){
        try (Session session = driver.session()){
            session.run("CREATE (Customer {customer: $customer})", parameters("customer", customer));
        }
    }
    
    public void addCategory(String category){
        try (Session session = driver.session()){
            session.run("CREATE (Category {category: $category})", parameters( "category", category));
        }
    }

    
    public void createData(){
        try (Session session = driver.session()){
//        	session.run("MATCH (a:Product {product:'product1'}),(b:Category{category:'category1'}) CREATE (a)-[r:IS_IN]->(b)"); 
//        	session.run("MATCH (a:Product {product:'product2'}),(b:Category{category:'category1'}) CREATE (a)-[r:IS_IN]->(b)");
//        	session.run("MATCH (a:Product {product:'product3'}),(b:Category{category:'category1'}) CREATE (a)-[r:IS_IN]->(b)");
//        	session.run("MATCH (a:Product {product:'product4'}),(b:Category{category:'category1'}) CREATE (a)-[r:IS_IN]->(b)");
//        	session.run("MATCH (a:Product {product:'product5'}),(b:Category{category:'category2'}) CREATE (a)-[r:IS_IN]->(b)");
//        	session.run("MATCH (a:Product {product:'product6'}),(b:Category{category:'category2'}) CREATE (a)-[r:IS_IN]->(b)");
//        	session.run("MATCH (a:Product {product:'product7'}),(b:Category{category:'category2'}) CREATE (a)-[r:IS_IN]->(b)");
//        	session.run("MATCH (a:Product {product:'product8'}),(b:Category{category:'category2'}) CREATE (a)-[r:IS_IN]->(b)");
//        	session.run("MATCH (a:Product {product:'product9'}),(b:Category{category:'category3'}) CREATE (a)-[r:IS_IN]->(b)");
//        	session.run("MATCH (a:Product {product:'product10'}),(b:Category{category:'category3'}) CREATE (a)-[r:IS_IN]->(b)");
//        	session.run("MATCH (a:Product {product:'product11'}),(b:Category{category:'category3'}) CREATE (a)-[r:IS_IN]->(b)");
//        	session.run("MATCH (a:Product {product:'product12'}),(b:Category{category:'category3'}) CREATE (a)-[r:IS_IN]->(b)");
//        	
//        	session.run("MATCH (a:Customer {customer:'customer1'}),(b:Product{product:'product1'}) CREATE (a)-[r:ADDED_TO_WISH_LIST]->(b)");
//        	session.run("MATCH (a:Customer {customer:'customer2'}),(b:Product{product:'product4'}) CREATE (a)-[r:ADDED_TO_WISH_LIST]->(b)");
//        	session.run("MATCH (a:Customer {customer:'customer2'}),(b:Product{product:'product7'}) CREATE (a)-[r:ADDED_TO_WISH_LIST]->(b)");
//        	session.run("MATCH (a:Customer {customer:'customer3'}),(b:Product{product:'product9'}) CREATE (a)-[r:ADDED_TO_WISH_LIST]->(b)");
//        	session.run("MATCH (a:Customer {customer:'customer3'}),(b:Product{product:'product11'}) CREATE (a)-[r:ADDED_TO_WISH_LIST]->(b)");
//        	session.run("MATCH (a:Customer {customer:'customer4'}),(b:Product{product:'product12'}) CREATE (a)-[r:ADDED_TO_WISH_LIST]->(b)");
//        	
//        	session.run("MATCH (a:Customer {customer:'customer1'}),(b:Product {product:'product1'}) CREATE (a)-[r:VIEWED]->(b)");
//        	session.run("MATCH (a:Customer {customer:'customer2'}),(b:Product {product:'product4'}) CREATE (a)-[r:VIEWED]->(b)");
//        	session.run("MATCH (a:Customer {customer:'customer3'}),(b:Product {product:'product9'}) CREATE (a)-[r:VIEWED]->(b)");
//        	session.run("MATCH (a:Customer {customer:'customer4'}),(b:Product {product:'product12'}) CREATE (a)-[r:VIEWED]->(b)");
//        	
//        	session.run("MATCH (a:Customer {customer:'customer1'}),(b:Product {product:'product1'}) CREATE (a)-[r:BOUGHT]->(b)");
//        	session.run("MATCH (a:Customer {customer:'customer2'}),(b:Product {product:'product7'}) CREATE (a)-[r:BOUGHT]->(b)");
//        	session.run("MATCH (a:Customer {customer:'customer3'}),(b:Product {product:'product11'}) CREATE (a)-[r:BOUGHT]->(b)");
//        	session.run("MATCH (a:Customer {customer:'customer4'}),(b:Product {product:'product12'}) CREATE (a)-[r:BOUGHT]->(b)");
        	
        	session.run("CREATE (smartphones:Category {title: 'Smartphones'}), \r\n"
        			+ "(notebooks:Category {title: 'Notebooks'}), \r\n"
        			+ "(cameras:Category {title: 'Cameras'})\r\n"
        			+ "\r\n"
        			+ "// Smartphones\r\n"
        			+ "CREATE (sony_xperia_z22:Product {title: 'Sony Experia Z22', price: 765.00, shippability: true, availability: true})\r\n"
        			+ "CREATE (samsung_galaxy_s8:Product {title: 'Samsung Galaxy S8', price: 784.00, shippability: true, availability: true})\r\n"
        			+ "CREATE (sony_xperia_xa1:Product {title: 'Sony Xperia XA1 Dual G3112', price: 229.50, shippability: true, availability: false})\r\n"
        			+ "CREATE (iphone_8:Product {title: 'Apple iPhone 8 Plus 64GB', price: 874.20, shippability: true, availability: false})\r\n"
        			+ "CREATE (xiaomi_mi_mix_2:Product {title: 'Xiaomi Mi Mix 2', price: 420.87, shippability: true, availability: true})\r\n"
        			+ "CREATE (huawei_p8:Product {title: 'Huawei P8 Lite', price: 191.00, shippability: true, availability: true})\r\n"
        			+ "\r\n"
        			+ "MERGE (sony_xperia_z22)-[:IS_IN]->(smartphones)\r\n"
        			+ "MERGE (samsung_galaxy_s8)-[:IS_IN]->(smartphones)\r\n"
        			+ "MERGE (sony_xperia_xa1)-[:IS_IN]->(smartphones)\r\n"
        			+ "MERGE (iphone_8)-[:IS_IN]->(smartphones)\r\n"
        			+ "MERGE (xiaomi_mi_mix_2)-[:IS_IN]->(smartphones)\r\n"
        			+ "MERGE (huawei_p8)-[:IS_IN]->(smartphones)\r\n"
        			+ "\r\n"
        			+ "// Notebooks\r\n"
        			+ "CREATE (acer_swift_3:Product {title: 'Acer Swift 3 SF314-51-34TX', price: 595.00, shippability: true, availability: false})\r\n"
        			+ "CREATE (hp_pro_book:Product {title: 'HP ProBook 440 G4', price: 771.30, shippability: true, availability: true})\r\n"
        			+ "CREATE (dell_inspiron_15:Product {title: 'Dell Inspiron 15 7577', price: 1477.50, shippability: true, availability: true})\r\n"
        			+ "CREATE (apple_macbook:Product {title: \"Apple MacBook A1534 12' Rose Gold\", price: 1293.00, shippability: false, availability: true})\r\n"
        			+ "\r\n"
        			+ "MERGE (acer_swift_3)-[:IS_IN]->(notebooks)\r\n"
        			+ "MERGE (hp_pro_book)-[:IS_IN]->(notebooks)\r\n"
        			+ "MERGE (dell_inspiron_15)-[:IS_IN]->(notebooks)\r\n"
        			+ "MERGE (apple_macbook)-[:IS_IN]->(notebooks)\r\n"
        			+ "\r\n"
        			+ "// Cameras\r\n"
        			+ "CREATE (canon_eos_6d:Product {title: 'Canon EOS 6D Mark II Body', price: 1794.00, shippability: true, availability: false})\r\n"
        			+ "CREATE (nikon_d7500:Product {title: 'Nikon D7500 Kit 18-105mm VR', price: 1612.35, shippability: true, availability: true})\r\n"
        			+ "\r\n"
        			+ "MERGE (canon_eos_6d)-[:IS_IN]->(cameras)\r\n"
        			+ "MERGE (nikon_d7500)-[:IS_IN]->(cameras)\r\n"
        			+ "// Customers\r\n"
        			+ "CREATE (joe:Customer {name: 'Joe Baxton', email: 'joeee_baxton@example.com', age: 25})\r\n"
        			+ "CREATE (daniel:Customer {name: 'Daniel Johnston', email: 'dan_j@example.com', age: 31})\r\n"
        			+ "CREATE (alex:Customer {name: 'Alex McGyver', email: 'mcgalex@example.com', age: 22})\r\n"
        			+ "CREATE (alisson:Customer {name: 'Allison York', email: 'ally_york1@example.com', age: 24})\r\n"
        			+ "\r\n"
        			+ "MERGE (joe)-[:VIEWED {views_count: 15}]->(nikon_d7500)\r\n"
        			+ "MERGE (joe)-[:ADDED_TO_WISH_LIST]->(iphone_8)\r\n"
        			+ "MERGE (joe)-[:BOUGHT]->(apple_macbook)\r\n"
        			+ "\r\n"
        			+ "MERGE(daniel)-[:VIEWED {views_count: 10}]->(sony_xperia_z22)\r\n"
        			+ "MERGE(daniel)-[:VIEWED {views_count: 20}]->(dell_inspiron_15)\r\n"
        			+ "MERGE(daniel)-[:ADDED_TO_WISH_LIST]->(dell_inspiron_15)\r\n"
        			+ "\r\n"
        			+ "MERGE(alex)-[:VIEWED {views_count: 20}]->(canon_eos_6d)\r\n"
        			+ "MERGE(alex)-[:ADDED_TO_WISH_LIST]->(sony_xperia_xa1)\r\n"
        			+ "MERGE(alex)-[:ADDED_TO_WISH_LIST]->(nikon_d7500)\r\n"
        			+ "MERGE(alex)-[:BOUGHT]->(xiaomi_mi_mix_2)\r\n"
        			+ "\r\n"
        			+ "MERGE(alisson)-[:ADDED_TO_WISH_LIST]->(acer_swift_3)\r\n"
        			+ "MERGE(alisson)-[:ADDED_TO_WISH_LIST]->(hp_pro_book)\r\n"
        			+ "MERGE(alisson)-[:BOUGHT]->(huawei_p8)\r\n"
        			+ "MERGE(alisson)-[:BOUGHT]->(sony_xperia_xa1);");
        	
        }
    }
    
    private void classesSummary() {
        try (Session session = driver.session()) {        	
        	Result result = session.run("MATCH (n) WITH LABELS(n) AS labels, COUNT(n) AS freq"
        			+ " UNWIND labels AS label RETURN DISTINCT (label) AS label, freq");
            while (result.hasNext()) {
                Record record = result.next();
                System.out.println(record);
            }
        }       
    }
    
    private void nodeAttributesSummary() {
        try (Session session = driver.session()) {        	
        	Result result = session.run("MATCH (n:Customer) WITH KEYS(n) AS props, n"
        			+ " UNWIND props AS property RETURN DISTINCT property");
            while (result.hasNext()) { //change with a string param (n:Customer) would be $customer (example)
                Record record = result.next();
                System.out.println(record);
            }
        }       
    }
    
    private void relationsSummary() {
        try (Session session = driver.session()) {
        	Result result = session.run("MATCH (x)-[r]->(y) WITH LABELS(x) AS xlabels, LABELS(y) AS ylabels, COUNT(r) AS cnt"
        			+ "");
            while (result.hasNext()) {
                Record record = result.next();
                System.out.println(record);
            }
        }
    }
    
    private void resetDB(){
    	try (Session session = driver.session()){
    		session.run("MATCH (a)-[r]->() DELETE a, r");
    		session.run("MATCH (a) DELETE a");
    	}
    }
    
    @Override
    public void close() throws Exception{
        driver.close();
    }
	
	
	public static void main(String[] args) throws Exception{
		try (Neo4jTests session = new Neo4jTests("bolt://localhost:7687", "neo4j", "test123")) {
			
//			session.addProduct("product1");
//			session.addProduct("product2");
//			session.addProduct("product3");
//			session.addProduct("product4");
//			session.addProduct("product5");
//			session.addProduct("product6");
//			session.addProduct("product7");
//			session.addProduct("product8");
//			session.addProduct("product9");
//			session.addProduct("product10");
//			session.addProduct("product11");
//			session.addProduct("product12");
//			
//			session.addCustomer("customer1");
//			session.addCustomer("customer2");
//			session.addCustomer("customer3");
//			session.addCustomer("customer4");
//			
//			session.addCategory("category1");
//			session.addCategory("category2");
//			session.addCategory("category3");
			
			session.createData();
			session.classesSummary();
			session.nodeAttributesSummary();
			//session.printRelationship();
			//session.resetDB();
			
		}

	}

}
