package com.nikosera.common.builder;

import com.nikosera.common.exception.EmptyResultException;
import com.nikosera.common.exception.InvalidRequestException;
import com.nikosera.common.exception.NoResultFoundException;
import com.nikosera.common.constant.MsgConstant;

import java.util.function.Supplier;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class MsgBuilder {

    public static String successList(String model) {
        return String.format(MsgConstant.SuccessResponse.SUCCESS_FETCH_LIST, model);
    }

    public static String successSingle(String model) {
        return String.format(MsgConstant.SuccessResponse.SUCCESS_FETCH_SINGLE, model);
    }

    public static String successSave(String model) {
        return String.format(MsgConstant.SuccessResponse.SUCCESS_SAVE, model);
    }

    public static String successUpdate(String model) {
        return String.format(MsgConstant.SuccessResponse.SUCCESS_UPDATE, model);
    }

    public static String successStatus(String model) {
        return String.format(MsgConstant.SuccessResponse.SUCCESS_STATUS_CHANGE, model);
    }

    public static String build(String template, Object... args) {
        return String.format(template, args);
    }

    public static Supplier<EmptyResultException> emptyDataList(String type) {
        return () -> new EmptyResultException(build(MsgConstant.EMPTY_LIST_DATA, type));
    }

    public static Supplier<NoResultFoundException> doesntExist(String type) {
        return () -> new NoResultFoundException(build(MsgConstant.NO_DATA, type));
    }

    public static Supplier<NoResultFoundException> doesntExist(String type, Object searchParam) {
        return () -> new NoResultFoundException(build(MsgConstant.NO_DATA_WITH_DATA_NAME, type, searchParam));
    }

    public static Supplier<EmptyResultException> emptySearchData(String type) {
        return () -> new EmptyResultException(build(MsgConstant.EMPTY_SEARCH_DATA, type));
    }

    public static Supplier<InvalidRequestException> invalidRequest() {
        return () -> new InvalidRequestException(MsgConstant.INVALID_REQUEST);
    }

    public static Supplier<InvalidRequestException> invalidRequest(String msg) {
        return () -> new InvalidRequestException(msg);
    }
}
