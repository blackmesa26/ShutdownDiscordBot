import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.annotation.Nonnull;
import java.io.IOException;

public class MessageHandler extends ListenerAdapter {

    public static boolean ShutdownBool = false;

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        shutdown(event);
    }

    public void shutdown(@Nonnull MessageReceivedEvent event) {

        if (ShutdownBool == true && event.getMessage().getContentDisplay().contains(".stop")) {
            ShutdownBool = false;
        }

        if (ShutdownBool == true) {
            if (!event.getAuthor().isBot()) {
                if (event.getMessage().getContentDisplay().contains("YOUR PASSWORD")) {
                    try {
                        event.getChannel().sendMessage("Выключаем...").submit();
                        String shutdownCommand = "shutdown.exe -s -t 0";
                        Runtime.getRuntime().exec(shutdownCommand);
                        System.exit(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    event.getChannel().sendMessage("Пароль не верный").submit();
                }
            }
        }

        if (event.getMessage().getContentDisplay().contains(".shutdown")) {
            event.getChannel().sendMessage("Введите пароль :").submit();
            if (ShutdownBool == false) ShutdownBool = true;
        }
    }
}