package hien.project.dao;

import hien.project.entity.Video;

import java.util.List;

public interface IVideoDAO {
	Video create(Video video);

	Video update(Video video);

	Video remove(int id);

	Video findById(int id);

	List<Video> findAll();

	List<Video> findByKeyword(String keyword);
}