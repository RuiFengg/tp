package seedu.address.logic.parser.client;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.client.FindClientCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientNamePredicate;
import seedu.address.model.client.ClientPhonePredicate;

/**
 * Parses input arguments and creates a new FindClientCommand object
 */
public class FindClientCommandParser implements Parser<FindClientCommand> {

    public static final String MULTIPLE_PARAMETERS = "Please only input one parameter.";
    public static final int NUM_ALLOWED_PARAMETERS = 1;
    /**
     * Parses the given {@code String} of arguments in the context of the FindClientCommand
     * and returns a FindClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindClientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE);

        if (!anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClientCommand.MESSAGE_USAGE));
        }

        if (areMultipleParametersPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE)) {
            throw new ParseException(MULTIPLE_PARAMETERS);
        }

        Predicate<Client> predicate = null;
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            predicate = new ClientNamePredicate(ParserUtil.parseName(
                argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            predicate = new ClientPhonePredicate(ParserUtil
                .parsePhone(argMultimap.getValue(PREFIX_PHONE)
                    .get()));
        }

        return new FindClientCommand(predicate);
    }

    /**
     * * Returns true if none of the prefixes contains empty {@code Optional} values
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if there is more than one input parameters.
     */
    private static boolean areMultipleParametersPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count()
            > NUM_ALLOWED_PARAMETERS;
    }
}