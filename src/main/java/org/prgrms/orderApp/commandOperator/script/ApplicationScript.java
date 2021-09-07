package org.prgrms.orderApp.commandOperator.script;

public class ApplicationScript {
    public static final String START_MESSAGE = "=== Voucher Program === \nType exit to exit the program\n" +
            "Type created to a new voucher\nType list to list all vouchers";

    public static final String INPUT_USER_SELECTED_MENU_NUMBER__GUIDE_MESSAGE="[1] CREATE_VOUCHER \n[2] SHOW ALL VOUCHER LIST \n" +
            "[3] CREATE ORDER(NOT READY) \n[4] SHOW ALL ORDER LIST(NOT READY) \n[5] SHOW ALL CUSTOMER LIST \n[6] CREATE COLLECTION " +
            "\n[7] EXIT ";

    public static final String INPUT_USER_SELECTED_VOUCHER_TYPE__GUIDE_MESSAGE ="[1] Fixed Amount Type \n[2] Percent Amount Type";
    public static final String INPUT_USER_SELECTED_VOUCHER_TYPE = "Amount Type : ";
    public static final String SHOW_VOUCHER_LIST__GUIDE_MESSAGE = "Show Voucher List ....................";
    public static final String SHOW_ALL_BLACKLIST__GUIDE_MESSAGE = "Show All Black List ....................";
    public static final String SHOW_ALL_CUSTOMER__GUIDE_MESSAGE = "Show All Customer List ....................";
    public static final String SUCCESS_AMOUNT_SAVE ="Saving Succeeded, Created Voucher Id: ";
    public static final String INPUT_AMOUNT__GUIDE_MESSAGE="Please Put The Amount To Save";
    public static final String INPUT_AMOUNT="Amount : ";
}
