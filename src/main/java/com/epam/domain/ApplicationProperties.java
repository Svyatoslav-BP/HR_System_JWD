package com.epam.domain;

import com.epam.util.PropertyReaderUtil;

public final class ApplicationProperties {
    private final  int connectionPoolSize;
    private final  int maxConnectionPoolSize;
    private final int poolIncreaseStep;

    private final String URL ;
    private final String USER;
    private final String PASSWORD;

    public ApplicationProperties() {
        PropertyReaderUtil.loadProperties();
        this.connectionPoolSize = Integer.parseInt(PropertyReaderUtil.getProperties().getProperty("connectionPoolSize"));
        this.maxConnectionPoolSize = Integer.parseInt(PropertyReaderUtil.getProperties().getProperty("maxConnectionPoolSize"));
        this.poolIncreaseStep = Integer.parseInt(PropertyReaderUtil.getProperties().getProperty("poolIncreaseStep"));
        this.URL = PropertyReaderUtil.getProperties().getProperty("URL");
        this.USER = PropertyReaderUtil.getProperties().getProperty("USER");
        this.PASSWORD = PropertyReaderUtil.getProperties().getProperty("PASSWORD");
    }

    public int getPoolIncreaseStep() {
        return poolIncreaseStep;
    }

    public int getConnectionPoolSize() {
        return connectionPoolSize;
    }

    public int getMaxConnectionPoolSize() {
        return maxConnectionPoolSize;
    }

    public String getURL() {
        return URL;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
}
