package de.hey_car.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


@Generated("com.querydsl.codegen.EntitySerializer")
public class QVehicleEntity extends EntityPathBase<VehicleEntity> {

    private static final long serialVersionUID = 258412446L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVehicleEntity vehicle = new QVehicleEntity("vehicle");

    public final QVehicleEntity company;

    public final StringPath modle = createString("model");
    public final StringPath make = createString("make");
    public final StringPath color = createString("color");

    public final NumberPath<Long> year = createNumber("year", Long.class);

    public final StringPath lastName = createString("lastName");

    public QVehicleEntity(String variable) {
        this(VehicleEntity.class, forVariable(variable), INITS);
    }

    public QVehicleEntity(Path<? extends VehicleEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVehicleEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVehicleEntity(PathMetadata metadata, PathInits inits) {
        this(VehicleEntity.class, metadata, inits);
    }

    public QVehicleEntity(Class<? extends VehicleEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("vehicle") ? new QVehicleEntity(forProperty("vehicle")) : null;
    }

}