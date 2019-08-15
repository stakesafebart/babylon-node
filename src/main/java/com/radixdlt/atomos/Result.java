package com.radixdlt.atomos;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Results of a constraint check
 */
public final class Result {
	private static final Result SUCCESS = new Result(false, null);
	private final boolean isError;
	private final String errorMsg;

	private Result(boolean isError, String errorMsg) {
		this.isError = isError;
		this.errorMsg = errorMsg;
	}

	/**
	 * Returns a result indicating success
	 */
	public static Result success() {
		return SUCCESS;
	}

	/**
	 * Returns a result indicating error with an error message
	 */
	public static Result error(String errorMsg) {
		return new Result(true, errorMsg);
	}

	/**
	 * Returns a result indicating success or error given the success boolean
	 * @param success The boolean indicating success or error
	 * @param messageIfError The message to supply if it's an error
	 * @return The result
	 */
	public static Result of(boolean success, Supplier<String> messageIfError) {
		if (success) {
			return SUCCESS;
		} else {
			return error(messageIfError.get());
		}
	}

	/**
	 * Returns a result indicating success or error given the success boolean
	 * @param success The boolean indicating success or error
	 * @param messageIfError The message if an error
	 * @return The result
	 */
	public static Result of(boolean success, String messageIfError) {
		if (success) {
			return SUCCESS;
		} else {
			return error(messageIfError);
		}
	}

	/**
	 * Returns whether constraint check this object represents was successful or not
	 *
	 * @return if success returns false, otherwise returns true
	 */
	public boolean isError() {
		return isError;
	}

	/**
	 * Returns whether constraint check this object represents was sucessful or not
	 *
	 * @return if success returns false, otherwise returns true
	 */
	public boolean isSuccess() {
		return !isError;
	}

	/**
	 * Returns singleton stream of the error message if an error exists.
	 * Useful for functional stream logic
	 *
	 * @return if error, singleton stream of error message, otherwise, an empty stream
	 */
	public Stream<String> errorStream() {
		return isError ? Stream.of(errorMsg) : Stream.empty();
	}

	/**
	 * Get the error message of this Result, exists if there is an error
	 * @return if error, contains the error message, empty otherwise
	 */
	public Optional<String> getErrorMessage() {
		return Optional.ofNullable(this.errorMsg);
	}
}
