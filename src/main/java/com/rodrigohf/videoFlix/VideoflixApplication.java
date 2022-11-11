package com.rodrigohf.videoFlix;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rodrigohf.videoFlix.domains.Video;
import com.rodrigohf.videoFlix.repositories.VideoFlixRepository;

@SpringBootApplication
public class VideoflixApplication implements CommandLineRunner {

	@Autowired
	private VideoFlixRepository videoFlixRepository;

	public static void main(String[] args) {
		SpringApplication.run(VideoflixApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Video vid1 = new Video(null, "Guns N Roses Nightrain Legendado", "Rock n Roll anos 90",
				"https://www.youtube.com/watch?v=7EPXGZLSmUs");

		Video vid2 = new Video(null, "Counting Crows - Mr. Jones (Official Music Video)",
				"Rock n Roll anos 90",
				"https://www.youtube.com/watch?v=-oqAU5VxFWs");

		videoFlixRepository.saveAll(Arrays.asList(vid1, vid2));

	}
}