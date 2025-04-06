package gb.zona_fit;
import gb.zona_fit.gui.ZonaFitForma;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import javax.swing.*;
import com.formdev.flatlaf.FlatDarculaLaf;
@SpringBootApplication
public class ZonaFitSwing {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        ConfigurableApplicationContext contextoSpring= new SpringApplicationBuilder(ZonaFitSwing.class).headless(false).
                web(WebApplicationType.NONE).run(args);
        SwingUtilities.invokeLater(()->{
         ZonaFitForma zonaFitForma=   contextoSpring.getBean(ZonaFitForma.class);
         zonaFitForma.setVisible(true);
        });
    }
}
