log4j {
    appender.stdout = "org.apache.log4j.ConsoleAppender"
    appender."stdout.layout"="org.apache.log4j.PatternLayout"
    rootLogger="error,stdout"
    logger {
        org.springframework="info,stdout"
    }
    additivity {
        org.springframework=false
    }
}