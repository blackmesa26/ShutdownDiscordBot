import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class App {
    public static void main(String[] args) throws Exception {
        JDA jda = new JDABuilder("YOUR TOKEN DISCORD BOT")
                .addEventListeners(new MessageHandler())
                .build();
    }
}
