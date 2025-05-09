public class RestaurantConsoleApp {
    private static final MenuService menuService = new MenuService();
    private static final OrderService orderService = new OrderService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        orderService.addNotifier(new EmailNotifier());
        int option;
        do {
            System.out.println("\n=== Sistema de Gestión de Pedidos ===");
            System.out.println("1. Agregar item al menú");
            System.out.println("2. Listar menú");
            System.out.println("3. Crear pedido");
            System.out.println("4. Procesar pedido");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (option) {
                case 1:
                    addMenuItem();
                    break;
                case 2:
                    listMenu();
                    break;
                case 3:
                    createOrder();
                    break;
                case 4:
                    processOrder();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 5);
    }

    private static void addMenuItem() {
        System.out.print("ID del Item: ");
        String id = scanner.nextLine();
        System.out.print("Nombre del Item: ");
        String name = scanner.nextLine();
        System.out.print("Precio: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Categoría: ");
        String category = scanner.nextLine();
        System.out.print("Descripción: ");
        String description = scanner.nextLine();

        MenuItem item = new MenuItem(id, name, price, category, description);
        menuService.addMenuItem(item);
        System.out.println("Item agregado al menú.");
    }

    private static void listMenu() {
        System.out.println("\n=== Menú ===");
        menuService.getAllItems().forEach((k, v) -> {
            System.out.println("- " + v.getName() + " ($" + v.getPrice() + ") - " + v.getCategory());
        });
    }

    private static void createOrder() {
        System.out.print("ID del Pedido: ");
        String orderId = scanner.nextLine();
        System.out.print("ID del Cliente: ");
        String customerId = scanner.nextLine();
        System.out.print("Nombre del Cliente: ");
        String customerName = scanner.nextLine();
        System.out.print("Dirección del Cliente: ");
        String address = scanner.nextLine();
        System.out.print("Teléfono del Cliente: ");
        String phone = scanner.nextLine();

        Customer customer = new Customer(customerId, customerName, address, phone);

        List<MenuItem> items = new ArrayList<>();
        String itemId;
        do {
            System.out.print("ID del Item (0 para terminar): ");
            itemId = scanner.nextLine();
            if (!itemId.equals("0")) {
                MenuItem item = menuService.getMenuItem(itemId);
                if (item != null) {
                    items.add(item);
                } else {
                    System.out.println("Item no encontrado.");
                }
            }
        } while (!itemId.equals("0"));

        if (!items.isEmpty()) {
            Order order = new Order(orderId, customer, items);
            orderService.createOrder(order);
        } else {
            System.out.println("No se agregó ningún item al pedido.");
        }
    }

    private static void processOrder() {
        System.out.print("ID del Pedido a procesar: ");
        String orderId = scanner.nextLine();
        orderService.processNextOrder(orderId);
    }
}
