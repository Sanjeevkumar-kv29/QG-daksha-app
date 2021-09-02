package com.quickghy.qgdaksha.util

import java.io.IOException

/**
 * @Author: Sanjeev Kumar
 * @Date: 27-07-2021
 */

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)