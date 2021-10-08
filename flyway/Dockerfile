FROM flyway/flyway

COPY conf/flyway_main.conf main.conf
COPY conf/flyway_seed.conf seed.conf

ENTRYPOINT ["-configFiles=main.conf", "migrate"]
ENTRYPOINT ["-configFiles=seed.conf", "migrate"]