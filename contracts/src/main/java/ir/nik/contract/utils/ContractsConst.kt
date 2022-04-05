package ir.nik.contract.utils

internal object ContractsConst {

    const val KEY_LOG_OUT= "logout"
    const val KEY_OFFLINE_MODE= "offline_mode"


    const val KEY_IMEI = "imei"
    const val KEY_OS_VERSION = "os_version"
    const val KEY_DEVICE_MODEL = "device_model"
    const val KEY_APP_VERSION = "app_version"
    const val KEY_HOST_NAME = "host_name"
    const val KEY_PERSONNEL_ID= "personnelId"
    const val KEY_ACCESS_TOKEN= "access_token"

    const val KEY_USER_ID= "user_id"
    const val KEY_DOCUMENT_START_DATE = "document_start_date"
    const val KEY_DOCUMENT_END_DATE = "document_end_date"
    const val KEY_START_DATE= "start_date"
    const val KEY_END_DATE= "end_date"

    const val KEY_SSID = "ssId"
    const val KEY_REPORT_NAME = "report_name"


    const val KEY_DC_ID: String = "dc_id"
    const val KEY_RELATED_TABLE_ID = "related_table_id"
    const val KEY_RELATED_TABLE_NAME = "related_table_name"

    const val KEY_CONTRACT = "CNT_ContractAttachment"


    object SubSystems{
        const val KEY_BUDGET = 98102 //بودجه
        const val KEY_CONTRACTS = 98103 //قراردادها
        const val KEY_DASHBOARD = 98105 //داشبورد
        const val KEY_REPORTS = 98107 //گزارشات
        const val KEY_CARD_BOARD = 98109 //کارتابل

        const val KEY_GOODS = 981030101 //قراردادها- اقلام کالا
    }

    object ContractGoods{
        const val KEY_GOODS = 98103010101L //کالا
        const val KEY_SERVICES = 98103010102L // خدمات
        const val KEY_PRICE_LIST = 98103010103L //فهرست بها
    }

    object ContractAttachment{
        const val CONTRACT_TEXT = 1301L
        const val CONTRACT_TEXT_DC_ID = 1810801L

        const val ATTACHMENTS = 1302L
        const val ATTACHMENTS_DC_ID = 1810803L

        const val TEMP_DELIVERY = 1303L
        const val TEMP_DELIVERY_DC_ID = 1810805L

        const val PERMANENT_DELIVERY = 1304L
        const val PERMANENT_DELIVERY_DC_ID = 1810807L

        const val ADDITION = 1305L
        const val ADDITION_DC_ID = 1810809L

        const val LAND_DELIVERY = 1307L
        const val LAND_DELIVERY_DC_ID = 1810811L

        const val WORKSHOP_DELIVERY = 1308L
        const val WORKSHOP_DELIVERY_DC_ID = 1810813L
    }

}