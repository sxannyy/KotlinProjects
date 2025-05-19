package com.example.dbproject;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ResultsDao_Impl implements ResultsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ResultEntity> __insertionAdapterOfResultEntity;

  private final EntityDeletionOrUpdateAdapter<ResultEntity> __deletionAdapterOfResultEntity;

  private final EntityDeletionOrUpdateAdapter<ResultEntity> __updateAdapterOfResultEntity;

  public ResultsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfResultEntity = new EntityInsertionAdapter<ResultEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `results` (`_id`,`name`,`result`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ResultEntity value) {
        stmt.bindLong(1, value.get_id());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getResult() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getResult());
        }
      }
    };
    this.__deletionAdapterOfResultEntity = new EntityDeletionOrUpdateAdapter<ResultEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `results` WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ResultEntity value) {
        stmt.bindLong(1, value.get_id());
      }
    };
    this.__updateAdapterOfResultEntity = new EntityDeletionOrUpdateAdapter<ResultEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `results` SET `_id` = ?,`name` = ?,`result` = ? WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ResultEntity value) {
        stmt.bindLong(1, value.get_id());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getResult() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getResult());
        }
        stmt.bindLong(4, value.get_id());
      }
    };
  }

  @Override
  public void insert(final ResultEntity... result) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfResultEntity.insert(result);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final ResultEntity result) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfResultEntity.handle(result);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ResultEntity... result) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfResultEntity.handleMultiple(result);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<ResultEntity>> getAll(final String order) {
    final String _sql = "SELECT * FROM results ORDER BY ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (order == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, order);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"results"}, false, new Callable<List<ResultEntity>>() {
      @Override
      public List<ResultEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfResult = CursorUtil.getColumnIndexOrThrow(_cursor, "result");
          final List<ResultEntity> _result = new ArrayList<ResultEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ResultEntity _item;
            final int _tmp_id;
            _tmp_id = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Integer _tmpResult;
            if (_cursor.isNull(_cursorIndexOfResult)) {
              _tmpResult = null;
            } else {
              _tmpResult = _cursor.getInt(_cursorIndexOfResult);
            }
            _item = new ResultEntity(_tmp_id,_tmpName,_tmpResult);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
