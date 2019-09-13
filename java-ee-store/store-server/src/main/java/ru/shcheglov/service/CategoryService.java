package ru.shcheglov.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.shcheglov.entity.Category;

/**
 * @author Alexey Shcheglov
 * @version dated 18.01.2019
 */

public interface CategoryService extends CommonService<Category> {

    String getParent(@Nullable String id);

    Category addOne(@NotNull String name);

}
