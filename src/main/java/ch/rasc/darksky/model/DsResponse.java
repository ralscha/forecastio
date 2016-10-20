/**
 * Copyright 2016-2016 Ralph Schaer <ralphschaer@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.rasc.darksky.model;

import java.math.BigDecimal;
import java.util.List;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * The response to a Dark Sky API call
 */
@Value.Immutable
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = ImmutableDsResponse.class)
public interface DsResponse {

	/**
	 * The requested latitude.
	 */
	BigDecimal latitude();

	/**
	 * The requested longitude.
	 */
	BigDecimal longitude();

	/**
	 * The IANA timezone name for the requested location (e.g. America/New_York). This is
	 * the timezone used for text forecast summaries and for determining the exact start
	 * time of daily data points. (Developers are advised to rely on local system settings
	 * rather than this value if at all possible: users may deliberately set an unusual
	 * timezone, and furthermore are likely to know what they actually want better than
	 * our timezone database does.)
	 */
	String timezone();

	/**
	 * The current timezone offset in hours from GMT. (This value is deprecated and should
	 * not be used.)
	 */
	int offset();

	/**
	 * An instance of {@link DsDataPoint} containing the current weather conditions at the
	 * requested location.
	 */
	@Nullable
	DsDataPoint currently();

	/**
	 * An instance of {@link DsDataPoint} containing the weather conditions
	 * minute-by-minute for the next hour.
	 */
	@Nullable
	DsDataBlock minutely();

	/**
	 * An instance of {@link DsDataPoint} containing the weather conditions hour-by-hour
	 * for the next two days.
	 */
	@Nullable
	DsDataBlock hourly();

	/**
	 * An instance of {@link DsDataPoint} containing the weather conditions day-by-day for
	 * the next week.
	 */
	@Nullable
	DsDataBlock daily();

	/**
	 * A collection of {@link DsAlert} instances, which, if present, contains any severe
	 * weather alerts, issued by a governmental weather authority, pertinent to the
	 * requested location.
	 */
	List<DsAlert> alerts();

	/**
	 * An instance of {@link DsFlag} containing miscellaneous metadata concerning this
	 * request.
	 */
	DsFlag flags();

}