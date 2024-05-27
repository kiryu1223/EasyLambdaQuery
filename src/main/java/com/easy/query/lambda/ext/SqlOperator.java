package com.easy.query.lambda.ext;

public enum SqlOperator
{
    POS("+"),
    NEG("-"),
    NOT("NOT"),
    COMPL("~"),
    PREINC("++"),
    PREDEC("--"),
    POSTINC("++"),
    POSTDEC("--"),
    OR("OR"),
    AND("AND"),
    BITOR("|"),
    BITXOR("^"),
    BITAND("&"),
    EQ("="),
    NE("<>"),
    LT("<"),
    GT(">"),
    LE("<="),
    GE(">="),
    SL("<<"),
    SR(">>"),
    USR(">>>"),
    PLUS("+"),
    MINUS("-"),
    MUL("*"),
    DIV("/"),
    MOD("%"),
    BITOR_ASG("|="),
    BITXOR_ASG("^="),
    BITAND_ASG("&="),
    SL_ASG("<<="),
    SR_ASG(">>="),
    USR_ASG(">>>="),
    PLUS_ASG("+="),
    MINUS_ASG("-="),
    MUL_ASG("*="),
    DIV_ASG("/="),
    MOD_ASG("%="),

    // sql only
    IS("IS"),
    IS_NULL("IS NULL"),
    IS_NOT("IS NOT"),
    IS_NOT_NULL("IS NOT NULL"),
    LIKE("LIKE"),
    IN("IN"),
    POW("POW"),
    CONCAT("CONCAT");
    ;

    private final String operator;

    SqlOperator(String operator)
    {
        this.operator = operator;
    }

    public String getOperator()
    {
        return operator;
    }
}
