package org.zerock.ex1.exception;

public enum MemberException {

    NOT_FOUND("NOT_FOUND", 404),
    DUPLICATE("DUPLICATE", 409),
    INVALID("INVALID", 400);

    private MemberTaskException memberTaskException;

    MemberException(String msg, int code) {
        memberTaskException = new MemberTaskException(msg, code);
    }

    public MemberTaskException get() {
        return memberTaskException;
    }
}
