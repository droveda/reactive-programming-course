package com.droveda.course.sec04.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/*
    - do the work only when it is subscribed
    - do the work based on the demand
    - stop producing when subscriber cancels
    - produce only the requested items
    - file should be closed once done
 */
public class FileReaderServiceImpl implements FileReaderService {

    private static final Logger log = LoggerFactory.getLogger(FileReaderServiceImpl.class);

    @Override
    public Flux<String> read(Path path) {
        return Flux.generate(
                () -> openFile(path),
                this::readFile,
                this::closeFile
        );
    }

    private BufferedReader openFile(Path path) throws IOException {
        log.info("Opening file: {}", path);
        return Files.newBufferedReader(path);
    }

    private BufferedReader readFile(BufferedReader reader, SynchronousSink<String> sink) {
        try {
            var line = reader.readLine();
            log.info("Reading line: {}", line);

            if (Objects.isNull(line)) {
                sink.complete();
            } else {
                sink.next(line);
            }
        } catch (IOException e) {
            sink.error(e);
        }

        return reader;
    }

    private void closeFile(BufferedReader reader) {
        try {
            reader.close();
            log.info("Closing file");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
