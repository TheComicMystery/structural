//Я придумав таку предметну область - сервіс нотаріальної контори. Коротко про НК:
//Додаються дані про клієнтів (ПІБ, необхідна послуга, адреса і телефон), і на підставі цього їм надаються обрані послуги.

package patterns;

public class Main {
    public static void main(String[] args) {
        //Factory Method
        ServiceFactory legalServiceFactory = new LegalServiceFactory();
        Service legalService = legalServiceFactory.createService();
        legalService.serve();

        ServiceFactory financialServiceFactory = new FinancialServiceFactory();
        Service financialService = financialServiceFactory.createService();
        financialService.serve();

        //Abstract Factory
        DocumentFactory legalDocumentFactory = new LegalDocumentFactory();
        Document legalDocument = legalDocumentFactory.createDocument();
        legalDocument.create();

        DocumentFactory financialDocumentFactory = new FinancialDocumentFactory();
        Document financialDocument = financialDocumentFactory.createDocument();
        financialDocument.create();

        //Builder
        Agreement agreement = new AgreementBuilder()
                .setClientName("Клієнт 1")
                .setServiceType("Юридична")
                .setAmount(1000.0)
                .setCommission(100.0)
                .setDescription("Опис угоди")
                .build();
        System.out.println(agreement);

        //Prototype
        try {
            Client originalClient = new Client("Пономарчук Василь Романович", "Юридичні послуги", "вул. Лесі Українки, 42", "123456789");
            Client clonedClient = originalClient.clone();
            System.out.println("Оригінальний клієнт: " + originalClient);
            System.out.println("Клонований клієнт: " + clonedClient);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        //Singleton
        Database database = Database.getInstance();
        database.connect();

        //Adapter
        DocumentServiceAdapter adapter = new DocumentServiceAdapter(legalDocument);
        adapter.serve();

        //Bridge
        AgreementImplementation legalImpl = new LegalAgreementImplementation();
        AgreementBridge legalAgreementBridge = new AgreementBridge(legalImpl);
        legalAgreementBridge.implement();

        AgreementImplementation financialImpl = new FinancialAgreementImplementation();
        AgreementBridge financialAgreementBridge = new AgreementBridge(financialImpl);
        financialAgreementBridge.implement();

        //Composite
        ComplexDocument complexDocument = new ComplexDocument();
        complexDocument.addDocument(new LegalDocument());
        complexDocument.addDocument(new FinancialDocument());
        complexDocument.create();

        //Decorator
        ServiceDecorator decoratedService = new AdditionalServiceDecorator(legalService);
        decoratedService.serve();

        //Facade
        AgreementFacade agreementFacade = new AgreementFacade(agreement);
        agreementFacade.executeAgreement();

        //Flyweight
        Service legalServiceFlyweight = ServiceFlyweightFactory.getService("Legal");
        Service financialServiceFlyweight = ServiceFlyweightFactory.getService("Financial");
        legalServiceFlyweight.serve();
        financialServiceFlyweight.serve();

        //Proxy
        DocumentProxy legalDocumentProxy = new DocumentProxy("Legal");
        legalDocumentProxy.create();

        DocumentProxy financialDocumentProxy = new DocumentProxy("Financial");
        financialDocumentProxy.create();
    }
}