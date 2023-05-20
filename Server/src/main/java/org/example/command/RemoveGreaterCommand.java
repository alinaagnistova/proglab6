package org.example.command;


import org.example.collection.CollectionManager;
import org.example.data.SpaceMarine;
import org.example.dtp.*;
import org.example.error.FileModeException;
import org.example.error.IllegalArgumentsException;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;


/**
 * Command remove_greater {element} : remove all elements from the collection that exceed the specified
 */
public class RemoveGreaterCommand extends BaseCommand implements CollectionEditor{
    private final CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        super("remove_greater", " {element} : удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнить команду
     * @param request аргументы команды
     * @throws  IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();
        try {
            if (Objects.isNull(request.getObject())) {
                return new Response(ResponseStatus.ASK_OBJECT, "Для команды " + this.getName() + " требуется объект");
            }
            Collection<SpaceMarine> toRemove = collectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .filter(spaceMarine -> spaceMarine.compareTo(request.getObject()) >= 1)
                    .toList();
            collectionManager.removeSpaceMarines(toRemove);
            return new Response(ResponseStatus.OK, "Удалены элементы большие чем заданный");
        } catch (NoSuchElementException e) {
            return new Response(ResponseStatus.ERROR, "В коллекции нет элементов");
        } catch (FileModeException e) {
            return new Response(ResponseStatus.ERROR, "Поля в файле не валидны! Объект не создан");
        }
    }
    }

