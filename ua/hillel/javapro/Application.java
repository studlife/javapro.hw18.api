package hillel.javapro;

import hillel.javapro.product.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) throws Exception {

        List<Product> list = new ArrayList<>();
            list.add(new Product("Book", 1.0, false, LocalDate.of(2022, 12, 30)));
            list.add(new Product("Fruit", 2.0, false, LocalDate.of(2022, 12, 20)));
            list.add(new Product("Book", 3.0, true, LocalDate.of(2022, 12, 10)));
            list.add(new Product("Cat", 4.0, true, LocalDate.of(2023, 1, 5)));
            list.add(new Product("Book", 55.0, false, LocalDate.of(2023, 1, 15)));
            list.add(new Product("Book", 45.0, false, LocalDate.of(2023, 1, 25)));



        // 1.2
        String typeProd = "Book";

        List<Product> books = list.stream()
                .filter(x -> x.getType().equals(typeProd))
                .collect(Collectors.toList());
        System.out.println("1.2 " + books);



        // 2.2
        double discount = 10.0;

        List<Product> booksDiscount = list.stream()
                .filter(x -> x.getType().equals("Book"))
                .filter(Product::getDiscount)
                .map(x -> new Product(x.getId(),
                        x.getType(), x.getPrice() * (1 - discount/100), x.getDiscount(), x.getCreateDate())                    )
                .collect(Collectors.toList());
        System.out.println("2.2 " + booksDiscount);



        // 3.2
        String typeProd2 = "Book";

        Optional<Product> minBookPrice = list.stream()
                .filter(x -> x.getType().equals(typeProd2))
                .min((x1, x2) -> x1.getPrice().compareTo(x2.getPrice()));
        Product prodMinBookPrice = null;
        try {
            prodMinBookPrice = minBookPrice.orElseThrow(() -> new Exception());
            System.out.println("3.2 " + prodMinBookPrice);
        } catch (Exception o) {
            System.out.println("3.3 Продукт Book не знайдено");
        }


        // 3.3
        String typeProd3 = "New";  // ищем продукт которого нет в базе

        Optional<Product> minNewPrice = list.stream()
                .filter(x -> x.getType().equals(typeProd3))
                .min((x1, x2) -> x1.getPrice().compareTo(x2.getPrice()));
        Product prodMinNewPrice = null;
        try {
            prodMinNewPrice = minNewPrice.orElseThrow(() -> new Exception());
            System.out.println("3.2 " + prodMinNewPrice);
        } catch (Exception o) {
            System.out.println("3.3 Продукт " + typeProd3 + " не знайдено");
        }



        // 4.2
        List<Product> lastDate = list.stream()
                .sorted((x1, x2) -> x2.getCreateDate().compareTo(x1.getCreateDate()))
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("4.2 " + lastDate);


        // 5.2
        Double sum = list.stream()
                .filter(x -> x.getCreateDate().getYear() == LocalDate.now().getYear())
                .filter(x -> x.getType().equals("Book"))
                .filter(x -> x.getPrice() <= 75)
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.println("5.2 " + sum);


        // 6.2
        Map<String, List<Product>> map = list.stream()
                .collect(Collectors.groupingBy(Product::getType, Collectors.toList()));
        System.out.println("6.2 " + map);

    }
}
