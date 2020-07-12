import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.annotation.Nonnull;
import java.io.IOException;

public class MessageHandler extends ListenerAdapter {

    public static boolean ShutdownBool = false;
    public static MessageChannel ChannelName;

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        shutdown(event);
    }

    public void shutdown(@Nonnull MessageReceivedEvent event) {
        System.out.println(event.getMessage());
        if (ShutdownBool == true && event.getMessage().getContentDisplay().contains(".stop")) {
            if (ChannelName == event.getChannel()) ShutdownBool = false;
        }

        if (ShutdownBool == true && ChannelName == event.getChannel()) {
            if (!event.getAuthor().isBot()) {
                if (event.getMessage().getContentDisplay().contains("password")) {
                    event.getChannel().sendMessage("Выключаем...").submit();
                    try {
                        String shutdownCommand = "shutdown.exe -f -p";
                        Runtime.getRuntime().exec(shutdownCommand);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    event.getChannel().sendMessage("Пароль не верный").submit();
                }
            }
        }

        if (event.getMessage().getContentDisplay().contains(".shutdown")) {
            ChannelName = event.getChannel();
            event.getChannel().sendMessage("Введите пароль :").submit();
            if (ShutdownBool == false) ShutdownBool = true;
        }
    }
}
