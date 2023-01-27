package hillel.javapro;

import hillel.javapro.product.Product;

import javax.xml.stream.Location;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<Product> finish1 = list.stream()
                .filter(x -> x.getType().equals("Book"))
                .collect(Collectors.toList());
        System.out.println("1.2 " + finish1);


        // 2.2
        List<Product> finish2 = list.stream()
                .filter(x -> x.getType().equals("Book") && x.getDiscount() == true)
                .map(x -> {
                    x.setPrice(x.getPrice()*1.1);
                    return x;
                })
                .collect(Collectors.toList());
        System.out.println("2.2 " + finish2);


        // 3.2 / 3.3
        Optional<Product> min = list.stream()
                .filter(x -> x.getType().equals("Book"))
                .limit(0)
                .min((x1, x2) -> x1.getPrice().compareTo(x2.getPrice()));
        Product product = null;
        try {
            product = min.orElseThrow(() -> new Exception());
            System.out.println("3.2 " + product);
        } catch (Exception o) {
            System.out.println("3.3 Продукт Book не знайдено");
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
                .mapToDouble(Product::getPrice).sum();
        System.out.println("5.2 " + sum);


        // 6.2
        Map<String, List<Product>> map = list.stream()
                .collect(Collectors.groupingBy(Product::getType, Collectors.toList()));
        System.out.println("6.2 " + map);

    }
}
