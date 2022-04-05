package ir.nik.contract.utils

import android.os.Environment
import java.io.File

const val SSID_AUTOMATION = 982
const val SSID_SUPERVISORS = 986
const val SSID_MANAGERS = 981
const val SSID_CONTRACTORS = 983
const val SSID_WAREHOUSE = 989
const val SSID_ASSET_MANAGEMENT = 917
const val SSID_VALUE_ENGINEERING = 906

internal const val DATABASE_NAME_MANAGERS = "CntManagers.db"
internal const val DATABASE_NAME_CONTRACTORS = "CntContractors.db"
internal const val DATABASE_NAME_SUPERVISORS = "CntSupervisors.db"

internal const val FARSI_NAME_MANAGERS = "پورتال مدیران"
internal const val FARSI_NAME_CONTRACTORS = "پورتال پیمانکاران"
internal const val FARSI_NAME_SUPERVISORS = "پورتال ناظران"

internal const val APP_NAME_MANAGERS = "CntManagers"
internal const val APP_NAME_CONTRACTORS = "CntContractors"
internal const val APP_NAME_SUPERVISORS = "CntSupervisors"

const val PAGE_SIZE = 50


val isSecure: Boolean
    get() = false


/** this settings will be changed on each subsystem-----------------------------**/

internal const val APP_NAME = APP_NAME_MANAGERS

internal const val KEY_PREFERENCE_NAME = APP_NAME_MANAGERS

internal const val SSID: Int = SSID_MANAGERS

internal const val DATABASE_NAME = DATABASE_NAME_MANAGERS

internal const val APP_NAME_FARSI: String = FARSI_NAME_MANAGERS

/** End settings-----------------------------------------------------------------**/


internal val PATH_STORAGE =
    Environment.getExternalStorageDirectory().toString() + File.separator + APP_NAME_FARSI

