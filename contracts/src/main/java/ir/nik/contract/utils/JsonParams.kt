package ir.nik.contract.utils

internal fun peymanCardJson(
    contractId: Long
): String{
    return "{\\\"CNT_ContractIDs_fk\\\":\\\"${contractId}\\\"}"
}

internal fun accountingDocumentJson(
    contractId: Long
): String{
    return "{\\\"CNT_ContractIDs_fk\\\":\\\"$contractId\\\",\\\"ReportName\\\":\\\"AR_ACC_AccountingTamrkoz2.mrt\\\",\\\"ReportKind\\\":\\\"0\\\"}"
}

internal fun contractFilterContractStatusJson(
    contractStatusId: Long
): String{
    return "{\\\"CNT_CsIDs_fk\\\":\\\"${contractStatusId}\\\"}"
}


internal fun contractStatusJson(
    contractId: Long
): String{
    return "{\\\"CNT_ContractIDs_fk\\\":\\\"${contractId}\\\"}"
}

internal fun contractExtendJson(
    contractId: Long
): String{
    return "{\\\"CNT_ContractIDs_fk\\\":\\\"${contractId}\\\"}"
}

internal fun contractDelayJson(
    contractId: Long
): String{
    return "{\\\"CNT_ContractIDs_fk\\\":\\\"${contractId}\\\"}"
}

internal fun subSystemJson(
    subSystemId: Int
): String{
    return "{\\\"NIK_SsParentIDs_fk\\\":\\\"${subSystemId}\\\"}"
}

internal fun contractGoodsJson(
    contractId: Long
): String{
    return "{\\\"CNT_ContractIDs_fk\\\":\\\"${contractId}\\\"}"
}

internal fun contractExecutiveJson(
    contractId: Long
): String{
    return "{\\\"CNT_ContractIDs_fk\\\":\\\"${contractId}\\\"}"
}

internal fun contractAttachmentJson(
    contractId: Long
): String{
    return "{\\\"CNT_ContractIDs_fk\\\":\\\"${contractId}\\\"}"
}


internal fun attachmentJson(
    relatedTableId: Long,
    dcIds: Long
): String{
    return "{\\\"DMS_DocumentRelatedTableId\\\":\\\"${relatedTableId}\\\",\\\"DMS_DcIDs_fk\\\":\\\"${dcIds}\\\"}"
}