/**
 * Copyright 2016 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.api;

import java.util.List;

import org.redisson.client.codec.Codec;
import org.redisson.codec.CodecProvider;
import org.redisson.config.Config;

/**
 * Main Redisson interface for access
 * to all redisson objects with reactive interface.
 *
 * @author Nikita Koksharov
 *
 */
public interface RedissonReactiveClient {

    /**
     * Returns readWriteLock instance by name.
     *
     * @param name - name of object
     * @return Lock object
     */
    RReadWriteLockReactive getReadWriteLock(String name);
    
    /**
     * Returns lock instance by name.
     * <p>
     * Implements a <b>non-fair</b> locking so doesn't guarantee an acquire order by threads.
     *
     * @param name - name of object
     * @return Lock object
     */
    RLockReactive getLock(String name);
    
    /**
     * Returns set-based cache instance by <code>name</code>.
     * Supports value eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getSet(String, Codec)}.</p>
     *
     * @param <V> type of values
     * @param name - name of object
     * @return SetCache object
     */
    <V> RSetCacheReactive<V> getSetCache(String name);

    /**
     * Returns set-based cache instance by <code>name</code>.
     * Supports value eviction with a given TTL value.
     *
     * <p>If eviction is not required then it's better to use regular map {@link #getSet(String, Codec)}.</p>
     *
     * @param <V> type of values
     * @param name - name of object
     * @param codec - codec for values
     * @return SetCache object
     */
    <V> RSetCacheReactive<V> getSetCache(String name, Codec codec);

    /**
     * Returns map-based cache instance by name
     * using provided codec for both cache keys and values.
     * Supports entry eviction with a given MaxIdleTime and TTL settings.
     * <p>
     * If eviction is not required then it's better to use regular map {@link #getMap(String, Codec)}.
     *
     * @param <K> type of keys
     * @param <V> type of values
     * @param name - name of object
     * @param codec - codec for values
     * @return MapCache object
     */
    <K, V> RMapCacheReactive<K, V> getMapCache(String name, Codec codec);

    /**
     * Returns map-based cache instance by <code>name</code>
     * using provided <code>codec</code> for both cache keys and values.
     * Supports entry eviction with a given MaxIdleTime and TTL settings.
     * <p>
     * If eviction is not required then it's better to use regular map {@link #getMap(String, Codec, MapOptions)}.
     * 
     * @param <K> type of key
     * @param <V> type of value
     * @param name - object name
     * @param codec - codec for keys and values
     * @param options - map options
     * @return MapCache object
     */
    <K, V> RMapCacheReactive<K, V> getMapCache(String name, Codec codec, MapOptions<K, V> options);

    /**
     * Returns map-based cache instance by name.
     * Supports entry eviction with a given MaxIdleTime and TTL settings.
     * <p>
     * If eviction is not required then it's better to use regular map {@link #getMap(String)}.
     *
     * @param <K> type of keys
     * @param <V> type of values
     * @param name - name of object
     * @return MapCache object
     */
    <K, V> RMapCacheReactive<K, V> getMapCache(String name);

    /**
     * Returns map-based cache instance by name.
     * Supports entry eviction with a given MaxIdleTime and TTL settings.
     * <p>
     * If eviction is not required then it's better to use regular map {@link #getMap(String, MapOptions)}.</p>
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param options - map options
     * @return MapCache object
     */
    <K, V> RMapCacheReactive<K, V> getMapCache(String name, MapOptions<K, V> options);
    
    /**
     * Returns object holder instance by name
     * 
     * @param <V> type of value
     * @param name - name of object
     * @return Bucket object
     */
    <V> RBucketReactive<V> getBucket(String name);

    /**
     * Returns object holder instance by name
     * using provided codec for object.
     *
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for value
     * @return Bucket object
     */
    <V> RBucketReactive<V> getBucket(String name, Codec codec);

    /**
     * Returns a list of object holder instances by a key pattern
     *
     * @param <V> type of value
     * @param pattern - pattern for name of buckets
     * @return list of buckets 
     */
    <V> List<RBucketReactive<V>> findBuckets(String pattern);

    /**
     * Returns HyperLogLog instance by name.
     * 
     * @param <V> type of values
     * @param name - name of object
     * @return HyperLogLog object
     */
    <V> RHyperLogLogReactive<V> getHyperLogLog(String name);

    /**
     * Returns HyperLogLog instance by name
     * using provided codec for hll objects.
     *
     * @param <V> type of values
     * @param name - name of object
     * @param codec - codec of values
     * @return HyperLogLog object
     */
    <V> RHyperLogLogReactive<V> getHyperLogLog(String name, Codec codec);

    /**
     * Returns list instance by name.
     *
     * @param <V> type of values
     * @param name - name of object
     * @return List object
     */
    <V> RListReactive<V> getList(String name);

    /**
     * Returns list instance by name
     * using provided codec for list objects.
     *
     * @param <V> type of values
     * @param name - name of object
     * @param codec - codec for values
     * @return List object
     */
    <V> RListReactive<V> getList(String name, Codec codec);

    /**
     * Returns map instance by name.
     *
     * @param <K> type of keys
     * @param <V> type of values
     * @param name - name of object
     * @return Map object
     */
    <K, V> RMapReactive<K, V> getMap(String name);

    /**
     * Returns map instance by name.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param options - map options
     * @return Map object
     */
    <K, V> RMapReactive<K, V> getMap(String name, MapOptions<K, V> options);

    /**
     * Returns map instance by name
     * using provided codec for both map keys and values.
     *
     * @param <K> type of keys
     * @param <V> type of values
     * @param name - name of object
     * @param codec - codec for keys and values
     * @return Map object
     */
    <K, V> RMapReactive<K, V> getMap(String name, Codec codec);

    /**
     * Returns map instance by name
     * using provided codec for both map keys and values.
     *
     * @param <K> type of key
     * @param <V> type of value
     * @param name - name of object
     * @param codec - codec for keys and values
     * @param options - map options
     * @return Map object
     */
    <K, V> RMapReactive<K, V> getMap(String name, Codec codec, MapOptions<K, V> options);

    /**
     * Returns set instance by name.
     *
     * @param <V> type of values
     * @param name - name of object
     * @return Set object
     */
    <V> RSetReactive<V> getSet(String name);

    /**
     * Returns set instance by name
     * using provided codec for set objects.
     *
     * @param <V> type of values
     * @param name - name of set
     * @param codec - codec for values
     * @return Set object
     */
    <V> RSetReactive<V> getSet(String name, Codec codec);

    /**
     * Returns Redis Sorted Set instance by name.
     * This sorted set sorts objects by object score.
     * 
     * @param <V> type of values
     * @param name of scored sorted set
     * @return ScoredSortedSet object
     */
    <V> RScoredSortedSetReactive<V> getScoredSortedSet(String name);

    /**
     * Returns Redis Sorted Set instance by name
     * using provided codec for sorted set objects.
     * This sorted set sorts objects by object score.
     * 
     * @param <V> type of values
     * @param name - name of scored sorted set
     * @param codec - codec for values
     * @return ScoredSortedSet object
     */
    <V> RScoredSortedSetReactive<V> getScoredSortedSet(String name, Codec codec);

    /**
     * Returns String based Redis Sorted Set instance by name
     * All elements are inserted with the same score during addition,
     * in order to force lexicographical ordering
     *
     * @param name - name of object
     * @return LexSortedSet object
     */
    RLexSortedSetReactive getLexSortedSet(String name);

    /**
     * Returns topic instance by name.
     *
     * @param <M> type of message
     * @param name - name of object
     * @return Topic object
     */
    <M> RTopicReactive<M> getTopic(String name);

    /**
     * Returns topic instance by name
     * using provided codec for messages.
     *
     * @param <M> type of message
     * @param name - name of object
     * @param codec - codec for message
     * @return Topic object
     */
    <M> RTopicReactive<M> getTopic(String name, Codec codec);

    /**
     * Returns topic instance satisfies by pattern name.
     *
     *  Supported glob-style patterns:
     *    h?llo subscribes to hello, hallo and hxllo
     *    h*llo subscribes to hllo and heeeello
     *    h[ae]llo subscribes to hello and hallo, but not hillo
     *
     * @param <M> type of message
     * @param pattern of the topic
     * @return PatternTopic object
     */
    <M> RPatternTopicReactive<M> getPatternTopic(String pattern);

    /**
     * Returns topic instance satisfies by pattern name
     * using provided codec for messages.
     *
     *  Supported glob-style patterns:
     *    h?llo subscribes to hello, hallo and hxllo
     *    h*llo subscribes to hllo and heeeello
     *    h[ae]llo subscribes to hello and hallo, but not hillo
     *
     * @param <M> type of message
     * @param pattern of the topic
     * @param codec - codec for message
     * @return PatternTopic object
     */
    <M> RPatternTopicReactive<M> getPatternTopic(String pattern, Codec codec);

    /**
     * Returns queue instance by name.
     *
     * @param <V> type of values
     * @param name - name of object
     * @return Queue object
     */
    <V> RQueueReactive<V> getQueue(String name);

    /**
     * Returns queue instance by name
     * using provided codec for queue objects.
     * 
     * @param <V> type of values
     * @param name - name of object
     * @param codec - codec for values
     * @return Queue object
     */
    <V> RQueueReactive<V> getQueue(String name, Codec codec);

    /**
     * Returns blocking queue instance by name.
     * 
     * @param <V> type of values
     * @param name - name of object
     * @return BlockingQueue object
     */
    <V> RBlockingQueueReactive<V> getBlockingQueue(String name);

    /**
     * Returns blocking queue instance by name
     * using provided codec for queue objects.
     * 
     * @param <V> type of values
     * @param name - name of object
     * @param codec - code for values
     * @return BlockingQueue object
     */
    <V> RBlockingQueueReactive<V> getBlockingQueue(String name, Codec codec);

    /**
     * Returns deque instance by name.
     * 
     * @param <V> type of values
     * @param name - name of object
     * @return Deque object
     */
    <V> RDequeReactive<V> getDeque(String name);

    /**
     * Returns deque instance by name
     * using provided codec for deque objects.
     * 
     * @param <V> type of values
     * @param name - name of object
     * @param codec - coded for values
     * @return Deque object
     */
    <V> RDequeReactive<V> getDeque(String name, Codec codec);

    /**
     * Returns "atomic long" instance by name.
     *
     * @param name of the "atomic long"
     * @return AtomicLong object
     */
    RAtomicLongReactive getAtomicLong(String name);

    /**
     * Returns bitSet instance by name.
     *
     * @param name - name of object
     * @return BitSet object
     */
    RBitSetReactive getBitSet(String name);

    /**
     * Returns script operations object
     *
     * @return Script object
     */
    RScriptReactive getScript();

    /**
     * Return batch object which executes group of
     * command in pipeline.
     *
     * See <a href="http://redis.io/topics/pipelining">http://redis.io/topics/pipelining</a>
     *
     * @return Batch object
     */
    RBatchReactive createBatch();

    /**
     * Returns keys operations.
     * Each of Redis/Redisson object associated with own key
     *
     * @return Keys object
     */
    RKeysReactive getKeys();

    /**
     * Shuts down Redisson instance <b>NOT</b> Redis server
     */
    void shutdown();

    /**
     * Allows to get configuration provided
     * during Redisson instance creation. Further changes on
     * this object not affect Redisson instance.
     *
     * @return Config object
     */
    Config getConfig();
    
    /**
     * Returns the CodecProvider instance
     * 
     * @return CodecProvider object
     */
    CodecProvider getCodecProvider();
    
    /**
     * Get Redis nodes group for server operations
     *
     * @return NodesGroup object
     */
    NodesGroup<Node> getNodesGroup();

    /**
     * Get Redis cluster nodes group for server operations
     *
     * @return NodesGroup object
     */
    NodesGroup<ClusterNode> getClusterNodesGroup();

    /**
     * Returns {@code true} if this Redisson instance has been shut down.
     *
     * @return <code>true</code> if this Redisson instance has been shut down otherwise <code>false</code>
     */
    boolean isShutdown();

    /**
     * Returns {@code true} if this Redisson instance was started to be shutdown
     * or was shutdown {@link #isShutdown()} already.
     *
     * @return <code>true</code> if this Redisson instance was started to be shutdown
     * or was shutdown {@link #isShutdown()} already otherwise <code>false</code>
     */
    boolean isShuttingDown();

}
