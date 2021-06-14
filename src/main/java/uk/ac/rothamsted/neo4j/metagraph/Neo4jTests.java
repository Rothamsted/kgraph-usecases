package uk.ac.rothamsted.neo4j.metagraph;

import static org.neo4j.driver.Values.parameters;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

/**
 * 
 * TODO: If you want to spend some time to familiarise with it, JUnit is the best way to write 
 * the multiple tests you have here:
 *
 * https://mkyong.com/maven/how-to-run-unit-test-with-maven/
 * https://baeldung-cn.com/junit-5-test-annotation
 * https://www.javatpoint.com/junit-tutorial (this doesn't use Maven)
 *
 */
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
    
    private List<Object[]> classesSummary() {
        try (Session session = driver.session()) {
        	List<Object[]> result1 = new ArrayList<> ();
        	Result result = session.run("MATCH (n) WITH LABELS(n) AS labels, COUNT(n) AS freq"
        			+ " UNWIND labels AS label RETURN DISTINCT (label) AS label, freq");
            while (result.hasNext()) {
                Record record = result.next();
                System.out.println(record);
                result1.add ( 
                	new Object[] { record.get ( "label" ).asString (), record.get ( "freq" ).asInt () } 
                );
            }
            return result1;
        }       
    }
    
    private void nodeAttributesSummary(String label) {
        try (Session session = driver.session()) {        	
        	Result result = session.run("MATCH (n:"+ label +") WITH KEYS(n) AS props, n"
        			+ " UNWIND props AS property RETURN DISTINCT property, COUNT(n) as freq");
            while (result.hasNext()) { 
                Record record = result.next();
                System.out.println(record);
            }
        }       
    }
    
    private void relationsSummary() {
        try (Session session = driver.session()) {
        	Result result = session.run("MATCH (x)-[r]->(y) WITH LABELS(x) AS xlabels, LABELS(y) AS ylabels, COUNT(r) AS cnt, TYPE(r) AS relType"
        			+ " UNWIND relType AS rel RETURN xlabels, rel, ylabels, cnt");
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
			
			session.createData();
			session.classesSummary();
			session.nodeAttributesSummary("Product");
			session.relationsSummary();
			//session.printRelationship();
			session.resetDB();
			
		}

	}

}
