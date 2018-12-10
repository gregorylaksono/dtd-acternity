package dtd.acternity.service.db.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dtd.acternity.service.model.Courier;
import dtd.acternity.service.model.Location;

public interface CourierRepository extends JpaRepository<Courier, Long> {


}
