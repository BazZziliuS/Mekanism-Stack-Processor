package org.cloudea.mekanismstackprocessor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.neoforged.fml.ModList;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class UpdateChecker {

    private static final String UPDATE_URL =
            "https://api.modrinth.com/updates/mekanism-stack-processor/forge_updates.json?neoforge=only";
    private static final String MC_VERSION = "1.21.1";

    static volatile boolean updateAvailable = false;
    static volatile String latestVersion = "";
    static volatile String currentVersion = "";

    public static void check() {
        currentVersion = ModList.get()
                .getModContainerById(MekanismStackProcessor.MODID)
                .map(c -> c.getModInfo().getVersion().toString())
                .orElse("unknown");

        Thread thread = new Thread(() -> {
            try (HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build()) {

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(UPDATE_URL))
                        .timeout(Duration.ofSeconds(10))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
                    JsonObject promos = json.getAsJsonObject("promos");

                    String remote = null;
                    if (promos.has(MC_VERSION + "-recommended")) {
                        remote = promos.get(MC_VERSION + "-recommended").getAsString();
                    } else if (promos.has(MC_VERSION + "-latest")) {
                        remote = promos.get(MC_VERSION + "-latest").getAsString();
                    }

                    if (remote != null && !remote.equals(currentVersion)) {
                        latestVersion = remote;
                        updateAvailable = true;
                        MekanismStackProcessor.LOGGER.warn(
                                "[MekanismStackProcessor] New version available: {} (current: {})",
                                latestVersion, currentVersion
                        );
                    } else {
                        MekanismStackProcessor.LOGGER.info(
                                "[MekanismStackProcessor] Mod is up to date ({})", currentVersion
                        );
                    }
                } else {
                    MekanismStackProcessor.LOGGER.warn(
                            "[MekanismStackProcessor] Update check failed, HTTP status: {}", response.statusCode()
                    );
                }
            } catch (Exception e) {
                MekanismStackProcessor.LOGGER.warn(
                        "[MekanismStackProcessor] Update check failed: {}", e.getMessage()
                );
            }
        }, "MekanismStackProcessor-UpdateChecker");
        thread.setDaemon(true);
        thread.start();
    }
}
