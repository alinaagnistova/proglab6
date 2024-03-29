package org.example.command;



import org.example.collection.CollectionManager;
import org.example.collection.CollectionUtil;
import org.example.dtp.Request;
import org.example.dtp.Response;
import org.example.dtp.ResponseStatus;
import org.example.error.CommandRuntimeException;
import org.example.error.ExitObligedException;
import org.example.error.IllegalArgumentsException;
import org.example.error.NoSuchIDException;

import java.util.Objects;


/**
 * Command update id {element} : update the value of the collection item whose id is equal to the given one
 */
public class UpdateIdCommand extends BaseCommand implements CollectionEditor{
    private final CollectionManager collectionManager;

    public UpdateIdCommand(CollectionManager collectionManager) {
        super("update", "update id {element}: обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }
    /**
     * Исполнить команду
     * @param request аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException{
        if (request.getArgs().isBlank()) throw new IllegalArgumentsException();
        try {
            long ID = Long.parseLong(request.getArgs().trim());
            if (!CollectionUtil.checkExist(ID)) throw new NoSuchIDException();
            if (Objects.isNull(request.getObject())){
                return new Response(ResponseStatus.ASK_OBJECT, "Для команды " + this.getName() + " требуется объект");
            }
            collectionManager.updateId(request.getObject(), ID);
            return new Response(ResponseStatus.OK, "Объект успешно обновлен\n");
        } catch (NoSuchIDException err) {
            return new Response(ResponseStatus.ERROR,"В коллекции нет элемента с таким id");
        } catch (NumberFormatException exception) {
            return new Response(ResponseStatus.ERROR,"id должно быть числом типа long");
        }
    }
}




