package data

/**
 *
 * @project api-automation-beautyreview-kotlin
 * @author fdn-faiz on 25/12/22
 */
object Constant {
    object BaseURL {
        const val REGION_PROD = "http://region.data.femaledaily.com"
        const val REGION_STAG = "http://region.data.femaledaily.com"
    }

    object HeaderAPI {
        const val VERSION = "1.5"
        const val KEY = "client01-pb3TVYisCGurD08ks3YW"
        const val DEVICE = "1"
    }

    object Telegram {
        const val CHANNEL_PROD = -1001390548611L
        const val CHANNEL_STAG = -1001360047092L
    }
}